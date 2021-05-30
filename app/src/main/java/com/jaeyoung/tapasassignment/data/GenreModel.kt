package com.jaeyoung.tapasassignment.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class GenreModel(
    val id: Int,
    val name: String,
    val abbr: String,
    val books: Boolean
) : Parcelable
