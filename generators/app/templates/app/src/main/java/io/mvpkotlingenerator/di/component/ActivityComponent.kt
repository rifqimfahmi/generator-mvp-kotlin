package <%= package %>.di.component

import <%= package %>.di.PerActivity
import <%= package %>.di.module.ActivityModule
import <%= package %>.ui.splash.SplashActivity
import dagger.Component

@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(splashActivity: SplashActivity)
}