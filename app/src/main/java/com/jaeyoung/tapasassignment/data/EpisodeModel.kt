package com.jaeyoung.tapasassignment.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class EpisodeModel(
    val id: Int,
    val title: String,
    val scene: Int,
    val free: Boolean,
    val downloadable: Boolean,
    val thumb: ThumbModel,
    val created_date: String,
    val nsfw: Boolean,
    val read: Boolean,
    val unlocked: Boolean,
    val nu: Boolean,
    val early_access: Boolean,
    val support_supporting_ad: Boolean,
    val view_cnt: Int,
    val scheduled_date: String?
) : Parcelable
