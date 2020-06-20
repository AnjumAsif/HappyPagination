package com.asif.base

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.asif.di.component.ActivityComponent
import com.asif.utility.Utils

abstract class BaseFragment : Fragment(), FragmentMvpView {
    private var mActivity: BaseActivity? = null
    private var mProgressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp(view)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity) {
            val activity: BaseActivity = context as BaseActivity
            mActivity = activity
            activity.onFragmentAttached()
        }
    }

    override fun showLoading() {
        hideLoading()
        mProgressDialog = Utils.showLoadingDialog(this.context!!)
    }

    override fun onSuccess() {
        hideLoading()
    }

    override fun hideLoading() {
        Log.e("mProgressDialog ", "AAAA $mProgressDialog AAAA")
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog!!.dismiss()
            mProgressDialog!!.cancel()
        }
    }

    override fun onError(message: String?) {
        if (mActivity != null) {
            mActivity!!.onError(message)
        }
    }

    override fun onError(@StringRes resId: Int) {
        if (mActivity != null) {
            mActivity!!.onError(resId)
        }
    }

    override fun showMessage(message: String?) {
        if (mActivity != null) {
            mActivity!!.showMessage(message)
        }
    }

    override fun showMessage(@StringRes resId: Int) {
        if (mActivity != null) {
            mActivity!!.showMessage(resId)
        }
    }

    override fun isNetworkConnected(): Boolean {
        return if (mActivity != null) {
            mActivity!!.isNetworkConnected()
        } else false
    }

    override fun onDetach() {
        mActivity = null
        super.onDetach()
    }

    override fun hideKeyboard() {
        if (mActivity != null) {
            mActivity!!.hideKeyboard()
        }
    }

    override fun openActivityOnTokenExpire() {
        if (mActivity != null) {
            mActivity!!.openActivityOnTokenExpire()
        }
    }

    open fun getActivityComponent(): ActivityComponent? {
        return if (mActivity != null) {
            mActivity!!.getActivityComponent()
        } else null
    }

    open fun getBaseActivity(): BaseActivity? {
        return mActivity
    }


    protected abstract fun setUp(view: View?)

    override fun onDestroy() {
        super.onDestroy()
    }

    interface Callback {
        fun onFragmentAttached()
        fun onFragmentDetached(tag: String?)
    }
}
