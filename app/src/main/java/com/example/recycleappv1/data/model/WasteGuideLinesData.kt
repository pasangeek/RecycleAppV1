package com.example.recycleappv1.data.model

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WasteGuideLinesData(
    var description: String? = "",
    var guidelines: String? = "",
    var recommendedBagImage: Uri = Uri.EMPTY,
    var remarks: String? = "",
    var type: String? = "",
    var typeOfWasteImage: Uri = Uri.EMPTY,
    var  isExpandable : Boolean = false
): Parcelable
