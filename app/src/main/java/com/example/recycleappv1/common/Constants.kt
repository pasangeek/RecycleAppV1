package com.example.recycleappv1.common

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

object FirebaseStorageConstants {
    val ROOT_DIRECTORY = "app"
    val NOTE_IMAGES = "note"
}
object SharedPrefConstants {
   // val sharedPref = "local_shared_pref"
    val USER_SESSION = "user_session"

    //val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
}


object Data_Store{

    const val DataStore_Name = "LOCATION_DATA"
    val Context.datastore : DataStore<Preferences> by  preferencesDataStore(name = DataStore_Name)


}