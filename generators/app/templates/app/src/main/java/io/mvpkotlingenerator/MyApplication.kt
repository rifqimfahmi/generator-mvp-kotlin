package <%= package %>

import android.app.Application
import <%= package %>.di.component.ApplicationComponent
import <%= package %>.di.component.DaggerApplicationComponent
import <%= package %>.di.module.ApplicationModule
import io.realm.Realm

/**
 * Created by rifqimfahmi on 12/02/18.
 */
open class MyApplication : Application() {

    lateinit var mAppComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)

        mAppComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()


    }
}