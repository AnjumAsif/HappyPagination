package com.asif.happypagination

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.multidex.MultiDexApplication
import com.coherant.asl.di.component.ApplicationComponent
import com.asif.di.module.ApplicationModule
import com.coherant.asl.di.component.DaggerApplicationComponent

class PaginationApplication : MultiDexApplication() {
    private var mApplicationComponent: ApplicationComponent? = null
    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity?, bundle: Bundle?) {
                if (activity != null) ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }
            override fun onActivityStarted(activity: Activity?) {}
            override fun onActivityResumed(activity: Activity?) {}
            override fun onActivityPaused(activity: Activity?) {}
            override fun onActivityStopped(activity: Activity?) {}
            override fun onActivitySaveInstanceState(
                activity: Activity?,
                bundle: Bundle?
            ) {
            }

            override fun onActivityDestroyed(activity: Activity?) {}
        })

    }

    companion object {
        operator fun get(context: Context): PaginationApplication? {
            return context.applicationContext as PaginationApplication
        }
    }

    fun getComponent(): ApplicationComponent? {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
        }
        return mApplicationComponent
    }

    // Needed to replace the component with a test specific one
    fun setComponent(applicationComponent: ApplicationComponent) {
        mApplicationComponent = applicationComponent
    }
}