package com.example.recycleappv1.ui.wastecatalog

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recycleappv1.common.Result
import com.example.recycleappv1.data.model.WasteGuideLinesData
import com.example.recycleappv1.data.sources.CommonImplRepository
import com.example.recycleappv1.data.sources.RecycleItemRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WasteCatalogViewModel@Inject constructor(
    private val recycleItemRepo: RecycleItemRepo,

    private val commonImplRepository: CommonImplRepository
) : ViewModel() {
    val responseGetWasteCatalog = MutableLiveData<Result>()
    private val wasteCatalogData = MutableLiveData<List<WasteGuideLinesData>>()

    init {
        // Initialize wasteCatalogData with the complete data
        wasteCatalogData.value = emptyList()
    }

    // Function to update the filtered data based on the search query


    fun getWasteCatalogData() {
        Log.d("WasteCatalogViewModel", "Fetching waste catalog data...")
        responseGetWasteCatalog.postValue(Result.Loading)
        recycleItemRepo.getWasteCatalogItems(getSavedCity()?:"")
            .addOnSuccessListener { queryDocumentSnapshots ->
                val list = ArrayList<WasteGuideLinesData>()
                queryDocumentSnapshots.documents.forEach { doc ->
                    val item = doc.toObject(WasteGuideLinesData::class.java)
                    if (item != null) {
                        list.add(item)
                    }
                }
                Log.d("WasteCatalogViewModel", "Waste catalog data loaded successfully")
                    responseGetWasteCatalog.postValue(Result.Success<List<WasteGuideLinesData>>(list))
                }
            .addOnFailureListener {
                responseGetWasteCatalog.postValue(Result.Failure("Failed data loading"))

            }
    }
    // Function to retrieve the saved city from the repository
    private fun getSavedCity() = commonImplRepository.getSavedCity()
}