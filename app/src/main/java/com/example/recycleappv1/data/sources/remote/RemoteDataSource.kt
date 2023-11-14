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
    /**
     * Retrieves recycler items for today in a specific city.
     *
     * @param city The name of the city.
     * @return A [Task] that resolves to a [QuerySnapshot] containing today's recycler items.
     */
    fun getTodayRecyclerItems(city: String): Task<QuerySnapshot> {
        val collectionRef = fireStore.collection("/Municipalities/${city}/recyclerItems")
        val calendar = Utils.getCurrentDate()
        val startTimestamp = Timestamp(calendar.time)
        val query = collectionRef.whereGreaterThanOrEqualTo("date", startTimestamp).limit(10)
        return query.get()
    }
    /**
     * Retrieves waste catalog items for a specific city.
     *
     * @param city The name of the city.
     * @return A [Task] that resolves to a [QuerySnapshot] containing waste catalog items.
     */
    fun getWasteCatalogItems(city: String): Task<QuerySnapshot> {
        val collectionRef = fireStore.collection("/Municipalities/${city}/wasteGuideLines")

        return collectionRef.get()
    }
    /**
     * Retrieves recycler data for a specific waste type in a city.
     *
     * @param city The name of the city.
     * @param wasteType The type of waste to filter by.
     * @return A [Task] that resolves to a [QuerySnapshot] containing recycler items for the specified waste type.
     */
    fun getRecyclerDataByWasteType(city: String, wastType: String): Task<QuerySnapshot> {
        val collectionRef = fireStore.collection("/Municipalities/${city}/recyclerItems")
        val query = collectionRef.whereEqualTo("wasteType", wastType)
        return query.get()
    }
    /**
     * Retrieves information about a city by its name.
     *
     * @param cityName The name of the city to retrieve information for.
     * @return A [Task] that resolves to a [DocumentSnapshot] containing city information.
     */
    fun getCityByName(cityName: String):  Task<DocumentSnapshot> {
        val collectionRef = fireStore.collection("Municipalities").document(cityName)
        return collectionRef.get()
    }

}