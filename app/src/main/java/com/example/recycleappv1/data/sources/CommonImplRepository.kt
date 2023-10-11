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

    override fun getNonBurnableReminderStatus()
            = localDataSource.getNonBurnableReminderStatus()


}