package com.example.recycleappv1.ui.onboarding.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recycleappv1.common.ErrorState
import com.example.recycleappv1.data.sources.CommonImplRepository
import com.example.recycleappv1.data.sources.RecycleItemRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val commonImplRepository: CommonImplRepository,
    private val recycleItemRepo: RecycleItemRepo,
) :
    ViewModel() {
    // LiveData to hold information about whether a location exists

    private var locationExistLiveData = MutableLiveData<Boolean>()
    // LiveData to hold the current city
    val city = MutableLiveData<String>()
    // LiveData to manage error states
    var errorState = MutableLiveData<ErrorState>()
    val errorLiveData: LiveData<ErrorState> get() = errorState

    // Function to save a city's name
    fun saveData(cityName: String) {
        // Save the provided city name using the commonImplRepository
        commonImplRepository.saveCity(cityName)
        // Update the city LiveData with the newly saved city name .
        // This notifies any active observers that the city value has changed,
        // delivering the new city name asynchronously on the main thread.
        city.postValue(cityName)
    }
    // Function to check if a location exists by city name
    fun checkLocationExist(cityName: String): MutableLiveData<Boolean> {
        recycleItemRepo.getCityByName(cityName).addOnSuccessListener {
            // If the city exists, update the locationExistLiveData to true
            locationExistLiveData.postValue(it.exists())
            // If there's a failure in retrieving the city, update locationExistLiveData to false
        }.addOnFailureListener {
            locationExistLiveData.postValue(false)
        }
        return locationExistLiveData
    }
}