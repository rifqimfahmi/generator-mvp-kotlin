package <%= package %>.data

import android.content.Context
import <%= package %>.data.prefs.PreferencesHelper
import <%= package %>.di.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

    @Singleton
    class AppDataManager @Inject constructor(@ApplicationContext context: Context,
                                             preferencesHelper: PreferencesHelper)
        : DataManager {

        val mContext: Context = context
        val mPreferencesHelper: PreferencesHelper = preferencesHelper

    }