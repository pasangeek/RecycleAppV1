package com.example.recycleappv1.model.repository

import com.example.recycleappv1.model.data.RecycleItemsData

interface RecycleItemsRepository {

    fun getRecycleItemsdata():List<RecycleItemsData>
}