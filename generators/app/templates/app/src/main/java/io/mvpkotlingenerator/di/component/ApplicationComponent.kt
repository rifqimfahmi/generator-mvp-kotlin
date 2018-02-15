package <%= package %>.di.component

import <%= package %>.data.DataManager
import <%= package %>.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

/*
 * Created by Rifqi Mulya Fahmi on 12/02/18.
 */

@Singleton
@Component(modules = arrayOf(
        ApplicationModule::class
))

interface ApplicationComponent {
    fun getDataManager() : DataManager
}