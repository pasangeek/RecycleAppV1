package com.example.recycleappv1.util

import com.example.recycleappv1.model.data.RecycleItemsData

sealed class UiState<out T> {
//loading /success /failure
    object Loading: UiState<Nothing>()
    data class Success<out T>(val data: T): UiState<T>()
    data class Failure(val error: String?): UiState<Nothing>()
}
