package com.example.recycleappv1.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recycleappv1.model.data.RecycleItemsData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

    ): ViewModel() {


   fun getRecycleItemsdata() {

      // _recycleItems.value = repository.getRecycleItemsdata()
   }

}