package com.example.recycleappv1.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recycleappv1.model.data.RecycleItemsData
import com.example.recycleappv1.model.repository.RecycleItemsRepository

class HomeViewModel (val repository: RecycleItemsRepository): ViewModel() {

    private val _recycleItems = MutableLiveData<List<RecycleItemsData>>()
    val recycleItems: LiveData<List<RecycleItemsData>>
            get() = _recycleItems
   fun getRecycleItemsdata() {
       _recycleItems.value = repository.getRecycleItemsdata()
   }

}