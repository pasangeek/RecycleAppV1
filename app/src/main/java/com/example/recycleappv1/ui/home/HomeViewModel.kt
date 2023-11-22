package com.example.recycleappv1.ui.home
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recycleappv1.common.Result
import com.example.recycleappv1.data.model.RecycleItemsData
import com.example.recycleappv1.data.sources.CommonImplRepository
import com.example.recycleappv1.data.sources.RecycleItemRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val recycleItemRepo: RecycleItemRepo,
    private val commonImplRepository: CommonImplRepository
    ): ViewModel() {

    private val TAG = "HomeViewModel"
    // LiveData to observe the result of getting recycling items for today

    val responseGetRecyclerItems = MutableLiveData<Result>()
    // Fetch today's waste data for a specified city
    fun getTodayWasteData(city: String) {
        Log.d(TAG, "Fetching today's waste data for city: $city")
        // Notify observers that data fetching is in progress
        responseGetRecyclerItems.postValue(Result.Loading)
        recycleItemRepo.getTodayRecyclerItems(city)
            .addOnSuccessListener { queryDocumentSnapshots ->
                val list = ArrayList<RecycleItemsData>()

                // Convert retrieved documents to RecycleItemsData object
                queryDocumentSnapshots.documents.forEach {
                    val item = it.toObject(RecycleItemsData::class.java)
                    if (item != null) {
                        list.add(item)
                    }

                } // Post the retrieved data to the LiveData as a Success result
                responseGetRecyclerItems.postValue(Result.Success<List<RecycleItemsData>>(list))
            }.addOnFailureListener {
                // Notify observers about failure in data loading
                responseGetRecyclerItems.postValue(Result.Failure("Failed data loading"))

            }
    }
    // Get the saved city from CommonImplRepository
    fun getSavedCity() = commonImplRepository.getSavedCity()
}