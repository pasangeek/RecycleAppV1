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
    fun saveCardBoardReminderStatus(status: Boolean) {
        sharePreferences.edit().putBoolean("CardBoardReminder", status).apply()
    }
    fun saveEmptyBottlesReminderStatus(status: Boolean) {
        sharePreferences.edit().putBoolean("EmptyBottlesReminder", status).apply()
    }
    fun saveCansReminderStatus(status: Boolean) {
        sharePreferences.edit().putBoolean("CansReminder", status).apply()
    }
    fun savePetReminderStatus(status: Boolean) {
        sharePreferences.edit().putBoolean("PetReminder", status).apply()
    }
    fun savePlasticReminderStatus(status: Boolean) {
        sharePreferences.edit().putBoolean("PlasticReminder", status).apply()
    }
    fun getNonBurnableReminderStatus() : Boolean{
        return sharePreferences.getBoolean("nonBurnableReminder", false)
    }
    fun getBurnableReminderStatus() : Boolean{
        return sharePreferences.getBoolean("BurnableReminder", false)
    }
    fun getCardBoardReminderStatus() : Boolean{
        return sharePreferences.getBoolean("CardBoardReminder", false)
    }
    fun getEmptyBottlesReminderStatus() : Boolean{
        return sharePreferences.getBoolean("EmptyBottlesReminder", false)
    }
    fun getCansReminderStatus() : Boolean{
        return sharePreferences.getBoolean("CansReminder", false)
    }
    fun getPetReminderStatus() : Boolean{
        return sharePreferences.getBoolean("PetReminder", false)

    }
    fun getPlasticReminderStatus() : Boolean{
        return sharePreferences.getBoolean("PlasticReminder", false)

    }
}