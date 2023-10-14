package com.example.recycleappv1.data.sources

interface LocationAbstract {
    fun saveCity(cityName:String)

    fun getSavedCity(): String?

    fun saveNonBurnableReminderStatus(status: Boolean)
    fun saveBurnableReminderStatus(status: Boolean)
    fun saveCardBoardReminderStatus(status: Boolean)

    fun saveEmptyBottlesReminderStatus(status: Boolean)
    fun saveCansReminderStatus(status: Boolean)
    fun PetReminderStatus(status: Boolean)
    fun savePlasticReminderStatus(status: Boolean)


    fun getNonBurnableReminderStatus() : Boolean

    fun getBurnableReminderStatus() : Boolean
    fun getCardBoardReminderStatus(): Boolean

    fun getEmptyBottlesReminderStatus(): Boolean
    fun getCansReminderStatus(): Boolean
    fun getPetReminderStatus(): Boolean

    fun getPlasticReminderStatus(): Boolean

}