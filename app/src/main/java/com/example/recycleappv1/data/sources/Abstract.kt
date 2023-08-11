package com.example.recycleappv1.data.sources

import com.example.recycleappv1.data.model.LocationData
import kotlinx.coroutines.flow.Flow

interface Abstract {
    suspend fun saveLocation(location_data :LocationData)
    suspend fun getLocation():Flow<LocationData>
}