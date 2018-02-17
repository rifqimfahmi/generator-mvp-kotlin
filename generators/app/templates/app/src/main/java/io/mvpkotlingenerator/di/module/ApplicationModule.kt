package <%= package %>.di.module

import android.app.Application
import android.content.Context
import <%= package %>.data.AppDataManager
import <%= package %>.data.DataManager
import <%= package %>.data.prefs.AppPreferencesHelper
import <%= package %>.data.prefs.PreferencesHelper
import <%= package %>.di.ApplicationContext
import <%= package %>.di.PreferenceInfo
import <%= package %>.util.AppConstants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule (application: Application) {

    val mApplication : Application = application

    @Provides
    @ApplicationContext
    fun provideContext(): Context = mApplication.applicationContext

    @Provides
    fun provideApplication(): Application = mApplication

    @Provides
    @Singleton
    fun provideDataManager(appDataManager: AppDataManager) : DataManager = appDataManager

    /*
        provide sharedPreferences need
     */

    @Provides
    @PreferenceInfo
    fun providePreferencesFileName(): String = AppConstants.PREF_NAME

    @Provides
    @Singleton
    fun providePreferencesHelper(appPreferencesHelper: AppPreferencesHelper) : PreferencesHelper = appPreferencesHelper
}