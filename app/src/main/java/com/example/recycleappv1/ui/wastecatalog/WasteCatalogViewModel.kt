package com.example.recycleappv1.ui.wastecatalog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recycleappv1.common.Result
import com.example.recycleappv1.data.model.RecycleItemsData
import com.example.recycleappv1.data.model.WasteGuideLinesData
import com.example.recycleappv1.data.sources.RecycleItemRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WasteCatalogViewModel@Inject constructor(
    private val recycleItemRepo: RecycleItemRepo
) : ViewModel() {
    val responseGetWasteCatalog = MutableLiveData<Result>()

    fun getWasteCatalogData() {
        responseGetWasteCatalog.postValue(Result.Loading)
        recycleItemRepo.getTodayRecyclerItems("kitakata")
            .addOnSuccessListener { queryDocumentSnapshots ->
                val list = ArrayList<WasteGuideLinesData>()
                queryDocumentSnapshots.documents.mapNotNull {

                    val item = it.toObject(WasteGuideLinesData::class.java)

                    if (item != null) {
                        list.add(item)
                    }

                    responseGetWasteCatalog.postValue(Result.Success<List<WasteGuideLinesData>>(list))
                }
            }.addOnFailureListener {
                responseGetWasteCatalog.postValue(Result.Failure("Failed data loading"))
            }
    }

}