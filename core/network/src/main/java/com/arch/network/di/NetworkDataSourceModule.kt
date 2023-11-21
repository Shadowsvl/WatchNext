package com.arch.network.di

import com.arch.network.data_source.WatchMediaNetwork
import com.arch.network.data_source.WatchMediaNetworkDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NetworkDataSourceModule {

    @Binds
    fun WatchMediaNetwork.binds(): WatchMediaNetworkDataSource
}