package <%= package %>.data.prefs

import android.content.Context
import android.content.SharedPreferences
import <%= package %>.di.ApplicationContext
import <%= package %>.di.PreferenceInfo
import javax.inject.Inject

/*
 * Created by Rifqi Mulya Fahmi on 13/02/18.
 */

class AppPreferencesHelper @Inject constructor(@ApplicationContext context: Context,
                                               @PreferenceInfo prefName: String)
    : PreferencesHelper {

    val mSharedPreferences: SharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)


}