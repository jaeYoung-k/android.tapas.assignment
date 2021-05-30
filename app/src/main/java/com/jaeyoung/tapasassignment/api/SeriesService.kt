package com.jaeyoung.tapasassignment.api

import com.jaeyoung.tapasassignment.data.BrowseModel
import com.jaeyoung.tapasassignment.data.EpisodeModel
import com.jaeyoung.tapasassignment.data.SeriesModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import io.reactivex.rxjava3.core.Single
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SeriesService {

    @GET("/browse/fresh")
    fun browseFresh(
        @Query("series_type") series_type: String,
        @Query("page") page: Int
    ): Single<Response<BrowseModel>>

    @GET("/series/{series_id}")
    fun getSeries(
        @Path("series_id") series_id: Int
    ): Single<Response<SeriesModel>>

    @GET("/series/{series_id}/episodes")
    fun getEpisodes(
        @Path("series_id") series_id: Int
    ): Single<Response<List<EpisodeModel>>>

    companion object {
        private const val BASE_URL =
            "https://f30ab4e8-ee15-415c-98b8-d2004c5d2b9e.mock.pstmn.io/"

        fun create(): SeriesService {
            val logger = HttpLoggingInterceptor().apply { level = Level.BODY }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
                .build()
                .create(SeriesService::class.java)
        }
    }
}