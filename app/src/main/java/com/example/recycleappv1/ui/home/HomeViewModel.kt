package com.example.recycleappv1.ui.home
import com.example.recycleappv1.common.Result
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recycleappv1.common.Utils
import com.example.recycleappv1.common.convertToCalendar
import com.example.recycleappv1.data.model.RecycleItemsData
import com.example.recycleappv1.data.sources.RecycleItemRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val recycleItemRepo: RecycleItemRepo
    ): ViewModel() {

    val responseGetRecyclerItems = MutableLiveData<Result>()
    fun getTodayRecyclerItems() {
        responseGetRecyclerItems.postValue(Result.Loading)
        recycleItemRepo.getTodayRecyclerItems("kitakata")
            .addOnSuccessListener { queryDocumentSnapshots ->
                val list = ArrayList<RecycleItemsData>()
                queryDocumentSnapshots.documents.forEach {

                    val item = it.toObject(RecycleItemsData::class.java)
                    if (item?.date!!.convertToCalendar().timeInMillis == Utils.getCurrentDate().timeInMillis) {
                        list.add(item)
                    }
                    responseGetRecyclerItems.postValue(Result.Success<List<RecycleItemsData>>(list))
                }
            }.addOnFailureListener {
                responseGetRecyclerItems.postValue(Result.Failure("Failed data loading"))
            }
    }

}