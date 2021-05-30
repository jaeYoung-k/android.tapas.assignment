package com.jaeyoung.tapasassignment.di

import com.jaeyoung.tapasassignment.api.SeriesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideSeriesService(): SeriesService {
        return SeriesService.create()
    }
}