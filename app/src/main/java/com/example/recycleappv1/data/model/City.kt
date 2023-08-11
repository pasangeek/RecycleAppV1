package com.example.recycleappv1.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class City(
    val id:Int, val name:String
): Parcelable

