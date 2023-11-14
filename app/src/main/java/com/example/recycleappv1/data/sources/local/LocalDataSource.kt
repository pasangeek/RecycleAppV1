package com.example.recycleappv1.data.sources.local

import android.content.SharedPreferences

import javax.inject.Inject
/**
 * A data source for handling local storage of reminder-related information using SharedPreferences.
 * This class implements the [IDataSource] interface.
 *
 * @property sharePreferences The SharedPreferences instance for storing and retrieving data.
 */
class LocalDataSource @Inject constructor(private val sharePreferences: SharedPreferences)  {
    /**
     * Save the selected city to SharedPreferences.
     *
     * @param cityName The name of the city to save.
     */
    fun saveCity(cityName: String) {
        sharePreferences.edit().putString("city", cityName).apply()
    }

    /**
     * Get the saved city from SharedPreferences.
     *
     * @return The name of the saved city, or null if not found.
     */
    fun getSavedCity(): String? {
        return sharePreferences.getString("city", null)
    }
    /**
     * Save the reminder status for non-burnable items to SharedPreferences.
     *
     * @param status The status of the non-burnable items reminder.
     */
    fun saveNonBurnableReminderStatus(status: Boolean) {
        sharePreferences.edit().putBoolean("nonBurnableReminder", status).apply()
    }
    /**
     * Save the reminder status for burnable items to SharedPreferences.
     *
     * @param status The status of the burnable items reminder.
     */
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