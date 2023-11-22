package com.example.recycleappv1.data.sources

import com.example.recycleappv1.data.sources.remote.RemoteDataSource
import javax.inject.Inject

class RecycleItemRepo @Inject constructor(
    private val remoteDataSource: RemoteDataSource
)
{
    // Retrieves recycling items for today in a specific city
    fun getTodayRecyclerItems(city: String) =
        remoteDataSource.getTodayRecyclerItems(city)
    // Retrieves waste catalog items for a specific cit
    fun getWasteCatalogItems(city: String) =
        remoteDataSource.getWasteCatalogItems(city)
    // Retrieves city data by its name
    fun getCityByName(cityName: String) =
        remoteDataSource.getCityByName(cityName)
    // Retrieves recycling data based on a specific waste type in a city
    fun getRecyclerDataByWasteType(city: String, wasteType: String) =
        remoteDataSource.getRecyclerDataByWasteType(city, wasteType)


}