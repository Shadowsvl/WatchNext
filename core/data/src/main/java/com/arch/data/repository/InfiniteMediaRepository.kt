package com.arch.data.repository

import com.arch.model.data.MediaType
import com.arch.model.data.WatchMedia
import kotlinx.coroutines.flow.Flow

interface InfiniteMediaRepository {

    fun getInfiniteMedia(mediaType: MediaType): Flow<List<WatchMedia>>

    suspend fun requestMoreMedia()

    suspend fun clearMedia()
}