package com.example.recycleappv1.data.sources.local

import android.content.SharedPreferences
import com.example.recycleappv1.data.sources.IDataSource
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val sharePreferences: SharedPreferences) : IDataSource {

    fun saveCity(cityName: String) {
        sharePreferences.edit().putString("city", cityName).apply()
    }

    fun getSavedCity(): String? {
        return sharePreferences.getString("city", null)
    }

    fun saveNonBurnableReminderStatus(status: Boolean) {
        sharePreferences.edit().putBoolean("nonBurnableReminder", status).apply()
    }

    fun saveBurnableReminderStatus(status: Boolean) {
        sharePreferences.edit().putBoolean("BurnableReminder", status).apply()
    }

    fun getNonBurnableReminderStatus() : Boolean{
        return sharePreferences.getBoolean("nonBurnableReminder", false)
    }
    fun getBurnableReminderStatus() : Boolean{
        return sharePreferences.getBoolean("BurnableReminder", false)
    }

}