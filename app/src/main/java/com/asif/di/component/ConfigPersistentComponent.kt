package com.asif.di.component

import com.asif.di.ConfigPersistent
import com.asif.di.component.ActivityComponent
import com.asif.di.module.ActivityModule
import com.coherant.asl.di.component.ApplicationComponent
import dagger.Component

@ConfigPersistent
@Component(dependencies = [ApplicationComponent::class])
interface ConfigPersistentComponent {
    fun activityComponent(activityModule: ActivityModule): ActivityComponent
}