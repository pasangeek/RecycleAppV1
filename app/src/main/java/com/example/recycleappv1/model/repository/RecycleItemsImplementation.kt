package com.example.recycleappv1.model.repository

import com.example.recycleappv1.model.data.RecycleItemsData
import com.google.firebase.firestore.FirebaseFirestore


class RecycleItemsImplementation ( val database : FirebaseFirestore):RecycleItemsRepository {

   override fun getRecycleItemsdata() : List<RecycleItemsData> {
        //get data from firebase
       return arrayListOf()
    }
}