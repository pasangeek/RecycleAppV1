package com.example.recycleappv1.model.repository

import com.example.recycleappv1.model.data.RecycleItemsData
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Date


class RecycleItemsImplementation ( val database : FirebaseFirestore):RecycleItemsRepository {

   override fun getRecycleItemsdata() : List<RecycleItemsData> {
        //get data from firebase
       return arrayListOf(
           RecycleItemsData(
               recycleItem = "something",
                      date = Date()

           ),
           RecycleItemsData(
               recycleItem = "something2",
               date = Date()
//testing branch..testing feature branch
           )
       )
    }
}