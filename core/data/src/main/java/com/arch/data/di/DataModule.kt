package com.arch.data.di

import com.arch.data.repository.DefaultInfiniteMediaRepository
import com.arch.data.repository.DefaultMediaRepository
import com.arch.data.repository.DefaultMoviesRepository
import com.arch.data.repository.DefaultSeriesRepository
import com.arch.data.repository.InfiniteMediaRepository
import com.arch.data.repository.MediaRepository
import com.arch.data.repository.MoviesRepository
import com.arch.data.repository.SeriesRepository
import com.arch.data.util.ConnectivityManagerNetworkMonitor
import com.arch.data.util.NetworkMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsMoviesRepository(
        defaultMoviesRepository: DefaultMoviesRepository
    ): MoviesRepository

    @Binds
    fun bindsSeriesRepository(
        defaultSeriesRepository: DefaultSeriesRepository
    ): SeriesRepository

    @Binds
    fun bindsMediaRepository(
        defaultMediaRepository: DefaultMediaRepository
    ): MediaRepository

    @Binds
    fun bindsInfiniteMediaRepository(
        defaultInfiniteMediaRepository: DefaultInfiniteMediaRepository
    ): InfiniteMediaRepository

    @Binds
    fun bindsNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor
    ): NetworkMonitor
}