package com.example.recycleappv1.ui.onboarding.location

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recycleappv1.data.model.LocationData
import com.example.recycleappv1.data.sources.CommonImplRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(private val commonImplRepository: CommonImplRepository):
    ViewModel(){

    var lat : MutableLiveData<String> = MutableLiveData("")
    var long : MutableLiveData<String> = MutableLiveData("")

    var location_data : MutableLiveData<LocationData> = MutableLiveData()
    val city = MutableLiveData<String>()
    fun saveData(cityName:String) {
        commonImplRepository.saveCity(cityName)
        city.postValue(cityName)
    }
}