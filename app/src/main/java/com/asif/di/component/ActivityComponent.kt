package com.asif.di.component

import com.asif.di.module.ActivityModule
import com.asif.happypagination.MainActivity
import dagger.Subcomponent

@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {
    /*Activity injection here*/
    fun inject(activity: MainActivity)
    }