package com.example.recycleappv1.data.model

import android.os.Parcelable
import com.google.firebase.firestore.ServerTimestamp
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class RecycleItemsData(
    var documentId: String? = "",
    var description: String?  = "",
    var wasteType: String?  = "",
    @ServerTimestamp
    var date: Date? = null,
    var iconUrl: String?  = ""
): Parcelable
