package com.jaeyoung.tapasassignment.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class ThumbModel(
    val width: Int,
    val height: Int,
    val file_size: Int,
    val file_url: String
) : Parcelable
