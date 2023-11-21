package com.arch.database.di

import com.arch.database.data_source.WatchMediaLocal
import com.arch.database.data_source.WatchMediaLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface LocalDataSourceModule {

    @Binds
    fun WatchMediaLocal.binds(): WatchMediaLocalDataSource
}