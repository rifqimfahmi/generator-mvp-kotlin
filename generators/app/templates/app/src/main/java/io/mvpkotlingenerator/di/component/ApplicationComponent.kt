package <%= package %>.di.component

import <%= package %>.data.DataManager
import <%= package %>.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        ApplicationModule::class
))

interface ApplicationComponent {
    fun getDataManager() : DataManager
}