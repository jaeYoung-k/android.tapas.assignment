package com.jaeyoung.tapasassignment.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class CreatorModel(
    val id: Int,
    val uname: String,
    val display_name: String,
    val profile_pic_url: String,
    val joined_creator_tip: Boolean
) : Parcelable