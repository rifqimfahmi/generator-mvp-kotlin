package com.rifqimfahmi.mvpkotlinbasetemplate.di.component

import com.rifqimfahmi.mvpkotlinbasetemplate.di.PerService
import com.rifqimfahmi.mvpkotlinbasetemplate.di.module.ServiceModule
import dagger.Component

@PerService
@Component(dependencies = [ApplicationComponent::class], modules = [ServiceModule::class])
interface ServiceComponent {
    // Inject your service here
}