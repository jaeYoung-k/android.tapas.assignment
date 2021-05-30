package com.jaeyoung.tapasassignment.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class PaginationModel(
    val page: Int,
    val has_next: Boolean
) : Parcelable
