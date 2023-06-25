package com.example.recycleappv1.model.data

import android.os.Parcelable
import com.google.firebase.firestore.ServerTimestamp
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class RecycleItemsData(
    var recycleItem : String,
    @ServerTimestamp
    var date : Date,

): Parcelable
