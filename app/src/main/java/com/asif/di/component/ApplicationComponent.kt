package com.coherant.asl.di.component

import android.app.Application
import android.content.Context
import com.asif.di.ApplicationContext
import com.asif.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    @ApplicationContext
    fun context(): Context

    fun application(): Application
}