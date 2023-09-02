package com.example.recycleappv1.data.sources

import com.example.recycleappv1.data.sources.local.LocalDataSource
import com.example.recycleappv1.data.sources.remote.RemoteDataSource
import javax.inject.Inject

class RecycleItemRepo @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource)
{

    fun getTodayRecyclerItems(city: String) =
        remoteDataSource.getTodayRecyclerItems(city)

    fun getWasteCatalogItems(city: String) =
        remoteDataSource.getWasteCatalogItems(city)

}