package com.example.recycleappv1.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecycleItemsData(
    var recycleItem : String,
    var date : String

): Parcelable
