package com.example.recycleappv1.data.sources

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.recycleappv1.data.model.LocationData
import kotlinx.coroutines.flow.map


const val DataStore_Name = "LOCATION_DATA"
val Context.datastore : DataStore<Preferences> by  preferencesDataStore(name = DataStore_Name)
class ImplRepository (private  val context: Context):LocationAbstract{

    companion object{

        //val LAT= doublePreferencesKey("LATITUTE")
     //   val LONG = doublePreferencesKey("LONGITUTE")
        val CITY = stringPreferencesKey("municipal_city")
    }

    override suspend fun saveLocation(location_data :LocationData) {
context.datastore.edit { locations ->
   // locations[LAT] = location_data.lat
    //locations[LONG] = location_data.long
    locations[CITY]=location_data.city
}
    }

    override suspend fun getLocation()= context.datastore.data.map { location_data ->
       LocationData(

         //  lat = location_data[LAT]!!,
          // long = location_data[LONG]!!,
           city = location_data[CITY]!!
       )
    }
}