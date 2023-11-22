package com.example.recycleappv1.ui.reminder

import android.util.Log
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
        Log.d("ReminderViewModel", "Fetching recycling data by waste type...")
        // Set loading state in LiveData
        responseGetRecyclerItems.postValue(Result.Loading)
        // Retrieve recycling data by type from the repository
        recycleItemRepo.getRecyclerDataByWasteType(getSavedCity()?:"", "Non Burnable")
            .addOnSuccessListener { queryDocumentSnapshots ->
                val list = ArrayList<RecycleItemsData>()
                // Iterate through documents and map them to RecycleItemsData
                queryDocumentSnapshots.documents.forEach {
                    val item = it.toObject(RecycleItemsData::class.java)
                    if (item != null) {
                        list.add(item)
                    }

                } // Post success result with the loaded recycling data
                responseGetRecyclerItems.postValue(Result.Success<List<RecycleItemsData>>(list))
                Log.d("ReminderViewModel", "Recycling data loaded successfully")
            }.addOnFailureListener {
                responseGetRecyclerItems.postValue(Result.Failure("Failed data loading"))
                Log.e("ReminderViewModel", "Failed to load recycling data: $it")
            }
    }
    fun getRecyclerDataByWasteTypeBurnable() {
        responseGetRecyclerItems.postValue(Result.Loading)
        recycleItemRepo.getRecyclerDataByWasteType(getSavedCity()?:"", "Burnable")
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


    fun getRecyclerDataByWasteTypeCardBoard() {
        responseGetRecyclerItems.postValue(Result.Loading)
        recycleItemRepo.getRecyclerDataByWasteType(getSavedCity()?:"", "CardBoard")
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
    fun getRecyclerDataByWasteTypeEmptyBottles() {
        responseGetRecyclerItems.postValue(Result.Loading)
        recycleItemRepo.getRecyclerDataByWasteType(getSavedCity()?:"", "Empty Bottles")
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
    fun getRecyclerDataByWasteTypeCans() {
        responseGetRecyclerItems.postValue(Result.Loading)
        recycleItemRepo.getRecyclerDataByWasteType(getSavedCity()?:"", "Cans")
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
    fun getRecyclerDataByWasteTypePet() {
        responseGetRecyclerItems.postValue(Result.Loading)
        recycleItemRepo.getRecyclerDataByWasteType(getSavedCity()?:"", "Pet Bottles")
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
    fun getRecyclerDataByWasteTypePlastic() {
        responseGetRecyclerItems.postValue(Result.Loading)
        recycleItemRepo.getRecyclerDataByWasteType(getSavedCity()?:"", "Plastic")
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
    fun getBurnableReminderStatus() : Boolean = commonImplRepository.getBurnableReminderStatus()
    fun getCardBoardReminderStatus():Boolean= commonImplRepository.getCardBoardReminderStatus()
    fun getEmptyBottlesReminderStatus():Boolean= commonImplRepository.getEmptyBottlesReminderStatus()
    fun getCansReminderStatus():Boolean= commonImplRepository.getCansReminderStatus()
    fun getPetReminderStatus():Boolean= commonImplRepository.getPetReminderStatus()
    fun getPlasticReminderStatus():Boolean= commonImplRepository.getPlasticReminderStatus()
    fun saveNonBurnableReminderStatus(status: Boolean) = commonImplRepository.saveNonBurnableReminderStatus(status)

    fun saveBurnableReminderStatus(status: Boolean) = commonImplRepository.saveBurnableReminderStatus(status)
    fun saveCardBoardReminderStatus(status: Boolean) = commonImplRepository.saveCardBoardReminderStatus(status)
    fun saveEmptyBottlesReminderStatus(status: Boolean) = commonImplRepository.saveEmptyBottlesReminderStatus(status)
    fun saveCansReminderStatus(status: Boolean) = commonImplRepository.saveCansReminderStatus(status)
    fun savePetReminderStatus(status: Boolean) = commonImplRepository.PetReminderStatus(status)
    fun savePlasticReminderStatus(status: Boolean) = commonImplRepository.savePlasticReminderStatus(status)
}