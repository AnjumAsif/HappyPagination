package com.asif.base

import android.app.ProgressDialog
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.asif.di.component.ActivityComponent
import com.asif.di.component.ConfigPersistentComponent
import com.asif.di.component.DaggerConfigPersistentComponent
import com.asif.di.module.ActivityModule
import com.asif.happypagination.PaginationApplication
import com.asif.utility.Utils
import java.util.concurrent.atomic.AtomicLong
import kotlin.collections.HashMap

open class BaseActivity : AppCompatActivity(), FragmentMvpView,
    BaseFragment.Callback {
    companion object {
        private const val KEY_ACTIVITY_ID = "KEY_ACTIVITY_ID"
        private val NEXT_ID =
            AtomicLong(0)
        private val sComponentsMap: HashMap<Long, ConfigPersistentComponent?> =
            HashMap()
    }
    var mActivityComponent: ActivityComponent? = null
    var mActivityId: Long = 0
    var mProgressDialog: ProgressDialog? = null
    var configPersistentComponent: ConfigPersistentComponent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Create the ActivityComponent and reuses cached ConfigPersistentComponent if this is
        // being called after a configuration change.
        mActivityId = savedInstanceState?.getLong(KEY_ACTIVITY_ID) ?: NEXT_ID.getAndIncrement()
        if (!sComponentsMap.containsKey(mActivityId)) {
            configPersistentComponent = DaggerConfigPersistentComponent.builder()
                .applicationComponent(PaginationApplication[this]!!.getComponent())
                .build()
            sComponentsMap[mActivityId] = configPersistentComponent
        } else {
            configPersistentComponent = sComponentsMap[mActivityId]
        }
        assert(configPersistentComponent != null)
        mActivityComponent = configPersistentComponent?.activityComponent(ActivityModule(this))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(KEY_ACTIVITY_ID, mActivityId)
    }


    fun getActivityComponent(): ActivityComponent? {
        return mActivityComponent
    }


    override fun onDestroy() {
        if (!isChangingConfigurations) {
            sComponentsMap.remove(mActivityId)
        }
        super.onDestroy()
    }

    fun activityComponent(): ActivityComponent? {
        return mActivityComponent
    }

    override fun showLoading() {
        hideLoading()
        mProgressDialog = Utils.showLoadingDialog(this)
    }

    override fun hideLoading() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog!!.dismiss()
            mProgressDialog!!.cancel()
        }
    }

    override fun openActivityOnTokenExpire() {}

    override fun onError(@StringRes resId: Int) {
        hideLoading()
    }

    override fun onError(message: String?) {
        hideLoading()
    }

    override fun onSuccess() {
        hideLoading()
    }

    override fun showMessage(message: String?) {}

    override fun showMessage(@StringRes resId: Int) {}

    override fun isNetworkConnected(): Boolean {
        return false
    }

    override fun hideKeyboard() {}

    override fun onFragmentAttached() {}

    override fun onFragmentDetached(tag: String?) {}
}
