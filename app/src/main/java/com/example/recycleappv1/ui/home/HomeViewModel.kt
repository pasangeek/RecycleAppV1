package com.example.recycleappv1.ui.home
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
    val responseGetRecyclerItems = MutableLiveData<Result>()
    fun getTodayWasteData(city: String) {
        responseGetRecyclerItems.postValue(Result.Loading)
        recycleItemRepo.getTodayRecyclerItems(city)
            .addOnSuccessListener { queryDocumentSnapshots ->
                val list = ArrayList<RecycleItemsData>()
                queryDocumentSnapshots.documents.forEach {
                    val item = it.toObject(RecycleItemsData::class.java)
                    if (item != null) {
                        list.add(item)
                    }

                }
                responseGetRecyclerItems.postValue(Result.Success<List<RecycleItemsData>>(list))
            }.addOnFailureListener {
                responseGetRecyclerItems.postValue(Result.Failure("Failed data loading"))
            }
    }
    fun getSavedCity() = commonImplRepository.getSavedCity()
}