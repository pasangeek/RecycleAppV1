package com.example.recycleappv1.data.sources

import com.example.recycleappv1.data.sources.local.LocalDataSource
import javax.inject.Inject

class CommonImplRepository  @Inject constructor(private val localDataSource: LocalDataSource,
) : LocationAbstract {

    override fun saveCity(cityName: String) {
        localDataSource.saveCity(cityName)
    }

    override fun getSavedCity() = localDataSource.getSavedCity()
    override fun saveNonBurnableReminderStatus(status: Boolean) {
        localDataSource.saveNonBurnableReminderStatus(status)
    }
    override fun saveBurnableReminderStatus(status: Boolean) {
        localDataSource.saveBurnableReminderStatus(status)
    }
    override fun saveCardBoardReminderStatus(status: Boolean) {
        localDataSource.saveCardBoardReminderStatus(status)
    }
    override fun saveEmptyBottlesReminderStatus(status: Boolean) {
        localDataSource.saveEmptyBottlesReminderStatus(status)
    }
    override fun saveCansReminderStatus(status: Boolean) {
        localDataSource.saveCansReminderStatus(status)
    }
    override fun PetReminderStatus(status: Boolean) {
        localDataSource.savePetReminderStatus(status)
    }
    override fun savePlasticReminderStatus(status: Boolean) {
        localDataSource.savePlasticReminderStatus(status)
    }
    override fun getNonBurnableReminderStatus()
            = localDataSource.getNonBurnableReminderStatus()

    override fun getBurnableReminderStatus()
            = localDataSource.getBurnableReminderStatus()
    override fun getCardBoardReminderStatus()
            = localDataSource.getCardBoardReminderStatus()

    override fun getEmptyBottlesReminderStatus()
            = localDataSource.getEmptyBottlesReminderStatus()
    override fun getCansReminderStatus()
            = localDataSource.getCansReminderStatus()
    override fun getPetReminderStatus()
            = localDataSource.getPetReminderStatus()
    override fun getPlasticReminderStatus()
            = localDataSource.getPlasticReminderStatus()



}