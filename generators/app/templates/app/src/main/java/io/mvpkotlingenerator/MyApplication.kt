package <%= package %>

import android.app.Application
import <%= package %>.di.component.ApplicationComponent
import <%= package %>.di.component.DaggerApplicationComponent
import <%= package %>.di.module.ApplicationModule
import com.squareup.leakcanary.LeakCanary
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by rifqimfahmi on 12/02/18.
 */
open class MyApplication : Application() {

    lateinit var mAppComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)

        Realm.init(this)
        val realmConfiguration = RealmConfiguration.Builder()
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(realmConfiguration)

        mAppComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()


    }
}