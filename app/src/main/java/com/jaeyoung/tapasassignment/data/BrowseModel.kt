package com.jaeyoung.tapasassignment.data

import kotlinx.serialization.Serializable

@Serializable
data class BrowseModel(
    val pagination: PaginationModel,
    val series: List<SeriesModel>
)