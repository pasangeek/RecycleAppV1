package com.example.recycleappv1.ui.reminder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recycleappv1.common.Result
import com.example.recycleappv1.data.model.RecycleItemsData
import com.example.recycleappv1.data.sources.CommonImplRepository
import com.example.recycleappv1.data.sources.RecycleItemRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReminderViewModel  @Inject constructor(
    private val recycleItemRepo: RecycleItemRepo,
    private val commonImplRepository: CommonImplRepository,
): ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is reminder Fragment"
    }
    val text: LiveData<String> = _text

    val responseGetRecyclerItems = MutableLiveData<Result>()
    fun getRecyclerDataByWasteType() {
        responseGetRecyclerItems.postValue(Result.Loading)
        recycleItemRepo.getRecyclerDataByWasteType(getSavedCity()?:"", "Non Burnable")
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

    private fun getSavedCity() = commonImplRepository.getSavedCity()

    fun getNonBurnableReminderStatus() : Boolean = commonImplRepository.getNonBurnableReminderStatus()
    fun saveNonBurnableReminderStatus(status: Boolean) = commonImplRepository.saveNonBurnableReminderStatus(status)
}