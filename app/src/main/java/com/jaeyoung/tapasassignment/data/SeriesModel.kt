package com.jaeyoung.tapasassignment.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class SeriesModel(
    val id: Int,
    val title: String,
    val type: String,
    val sale_type: String,
    val thumb: ThumbModel,
    val book_cover_url: String?,
    val creators: List<CreatorModel>,
    val age_rating: Int?,
    val rgb_hex: String,
    val restricted: Boolean,
    val restricted_msg: String?,
    val on_sale: Boolean,
    val discount_rate: Int,
    val sale_start_date: String?,
    val sale_end_date: String?,
    val subscribe_cnt: Int,
    val like_cnt: Int,
    val view_cnt: Int,
    val up: Boolean,
    val blurb: String,
    val sub_title: String,
    val genre: GenreModel,
    val rect_banner_url: String?
) :  Parcelable
