package com.example.recycleappv1.ui.onboarding.location

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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


    private var locationExistLiveData = MutableLiveData<Boolean>()
    val city = MutableLiveData<String>()
    fun saveData(cityName: String) {
        commonImplRepository.saveCity(cityName)
        city.postValue(cityName)
    }

    fun checkLocationExist(cityName: String): MutableLiveData<Boolean> {
        recycleItemRepo.getCityByName(cityName).addOnSuccessListener {
            locationExistLiveData.postValue(it.exists())
        }.addOnFailureListener {
            locationExistLiveData.postValue(false)
        }
        return locationExistLiveData
    }
}