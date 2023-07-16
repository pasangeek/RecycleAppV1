package com.example.recycleappv1.util

import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

object FireStoreCollection {
    val ITEM = "recycleItem"
    @ServerTimestamp
    val DATE = "date"
}