package com.example.recycleappv1.data.sources

import com.example.recycleappv1.data.sources.remote.RemoteDataSource
import javax.inject.Inject

class RecycleItemRepo @Inject constructor(
    private val remoteDataSource: RemoteDataSource
)
{

    fun getTodayRecyclerItems(city: String) =
        remoteDataSource.getTodayRecyclerItems(city)

    fun getWasteCatalogItems(city: String) =
        remoteDataSource.getWasteCatalogItems(city)

    fun getCityByName(cityName: String) =
        remoteDataSource.getCityByName(cityName)

    fun getRecyclerDataByWasteType(city: String, wasteType: String) =
        remoteDataSource.getRecyclerDataByWasteType(city, wasteType)


}