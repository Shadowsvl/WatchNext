package com.arch.database.di

import com.arch.database.AppDatabase
import com.arch.database.schema.dao.WatchMediaDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {

    @Provides
    fun providesWatchMediaDao(database: AppDatabase): WatchMediaDao = database.watchMediaDao()
}