package com.jaeyoung.tapasassignment.data

import kotlinx.serialization.Serializable

@Serializable
data class DetailModel(
    val series: SeriesModel? = null,
    val episodes: List<EpisodeModel>? = null
)
