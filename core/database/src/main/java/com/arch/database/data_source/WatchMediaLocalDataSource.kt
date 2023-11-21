package com.arch.database.data_source

import com.arch.common.result.Result
import com.arch.model.data.WatchMedia
import kotlinx.coroutines.flow.Flow

interface WatchMediaLocalDataSource {

    fun getMyListLatest(): Flow<List<WatchMedia>>

    fun isMediaAdded(id: Long): Flow<Boolean>

    suspend fun addMedia(media: WatchMedia)

    suspend fun removeMedia(media: WatchMedia)

    suspend fun getMedia(id: Long): Result<WatchMedia>

    fun getMyList(): Flow<List<WatchMedia>>

    suspend fun searchMyList(query: String): Result<List<WatchMedia>>
}