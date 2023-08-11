package com.example.recycleappv1.ui.onboarding.location

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recycleappv1.data.model.LocationData
import com.example.recycleappv1.data.sources.ImplRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(private val implRepository: ImplRepository):ViewModel(){

    var lat : MutableLiveData<String> = MutableLiveData("")
    var long : MutableLiveData<String> = MutableLiveData("")
    var city_ : MutableLiveData<String> = MutableLiveData("")
    var location_data : MutableLiveData<LocationData> = MutableLiveData()

    fun saveData(){
viewModelScope.launch (Dispatchers.IO){

    implRepository.saveLocation(
        LocationData(
           // lat = lat.toString(),
           // long = long.value.toDouble(),
            city = city_.value!!
        )

    )

}
    }
    fun  retrieveData(){
        viewModelScope.launch (Dispatchers.IO){
implRepository.getLocation().collect {

    location_data.postValue(it)
}

        }

    }
}