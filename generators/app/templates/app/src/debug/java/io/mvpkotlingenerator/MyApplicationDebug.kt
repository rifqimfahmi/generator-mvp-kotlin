package <%= package %>

import com.facebook.stetho.Stetho
import com.uphyca.stetho_realm.RealmInspectorModulesProvider

/*
 * Created by Rifqi Mulya Fahmi on 13/02/18.
 */

class MyApplicationDebug : MyApplication() {

    override fun onCreate() {
        super.onCreate()


        // Initiate Stetho
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build())
    }
}