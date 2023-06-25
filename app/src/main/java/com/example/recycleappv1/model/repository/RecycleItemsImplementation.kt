package com.example.recycleappv1.model.repository

import com.example.recycleappv1.model.data.RecycleItemsData


class RecycleItemsImplementation :RecycleItemsRepository {

   override fun getRecycleItemsdata() : List<RecycleItemsData> {
        //get data from firebase
       return arrayListOf()
    }
}