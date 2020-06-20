package com.asif.di.module

import android.app.Activity
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(activity: Activity) {
    private var mActivity: Activity = activity
    @Provides
    internal fun provideActivity(): Activity {
        return mActivity
    }

    @Provides
    internal fun providesContext(): Context {
        return mActivity
    }
}