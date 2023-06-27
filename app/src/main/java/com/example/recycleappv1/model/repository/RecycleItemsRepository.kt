package com.example.recycleappv1.model.repository

import com.example.recycleappv1.model.data.RecycleItemsData
import com.example.recycleappv1.util.UiState

interface RecycleItemsRepository {

    fun getRecycleItemsdata():UiState<List<RecycleItemsData>>
}