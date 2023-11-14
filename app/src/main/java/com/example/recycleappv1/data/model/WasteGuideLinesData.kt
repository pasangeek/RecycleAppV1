package com.example.recycleappv1.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WasteGuideLinesData(
    var description: String? = "",
    var guidelines: String? = "",
    var recommendedBagImage:  String? = "",
    var remarks: String? = "",
    var type: String? = "",
    var typeOfWasteImage:  String? = "",

): Parcelable
