package com.example.recycleappv1.data.sources.remote

import com.example.recycleappv1.common.Utils
import com.google.android.gms.tasks.Task
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val fireStore: FirebaseFirestore
) {
    fun getTodayRecyclerItems(city: String): Task<QuerySnapshot> {
        val collectionRef = fireStore.collection("/Municipalities/${city}/recyclerItems")
        val calendar = Utils.getCurrentDate()
        val startTimestamp = Timestamp(calendar.time)
        val query = collectionRef.whereGreaterThanOrEqualTo("date", startTimestamp).limit(10)
        return query.get()
    }

    fun getWasteCatalogItems(city: String): Task<QuerySnapshot> {
        val collectionRef = fireStore.collection("/Municipalities/${city}/wasteGuideLines")

        return collectionRef.get()
    }

    fun getRecyclerDataByWasteType(city: String, wastType: String): Task<QuerySnapshot> {
        val collectionRef = fireStore.collection("/Municipalities/${city}/recyclerItems")
        val query = collectionRef.whereEqualTo("wasteType", wastType)
        return query.get()
    }
    fun getCityByName(cityName: String):  Task<DocumentSnapshot> {
        val collectionRef = fireStore.collection("Municipalities").document(cityName)
        return collectionRef.get()
    }

}