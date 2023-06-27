package com.example.recycleappv1.model.repository

import com.example.recycleappv1.model.data.RecycleItemsData
import com.example.recycleappv1.util.UiState
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Date


class RecycleItemsImplementation ( val database : FirebaseFirestore):RecycleItemsRepository {

   override fun getRecycleItemsdata() : UiState<List<RecycleItemsData>> {
        //get data from firebase
val data = arrayListOf(
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
       if (data.isNullOrEmpty()){
           return  UiState.Failure("Data is Empty")
       }else {

           return UiState.Success(data)
       }
    }
}