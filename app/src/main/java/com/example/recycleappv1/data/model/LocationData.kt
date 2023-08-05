package com.example.recycleappv1.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationData(


    val lat: Double,
    val long: Double,
    val city: String

) : Parcelable
