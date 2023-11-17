package com.example.recycleappv1.common

sealed class ErrorState {
    data class NetworkError(val message: String) : ErrorState()
}