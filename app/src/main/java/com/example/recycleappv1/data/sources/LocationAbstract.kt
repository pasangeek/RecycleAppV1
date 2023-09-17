package com.example.recycleappv1.data.sources

interface LocationAbstract {
    fun saveCity(cityName:String)

    fun getSavedCity(): String?

    fun saveNonBurnableReminderStatus(status: Boolean)
    fun getNonBurnableReminderStatus() : Boolean
}