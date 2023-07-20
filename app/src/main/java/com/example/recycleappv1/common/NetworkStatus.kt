package com.example.recycleappv1.common

sealed class NetworkStatus
{
    object Unknown: NetworkStatus()
    object Connected: NetworkStatus()
    object Disconnected: NetworkStatus()
}