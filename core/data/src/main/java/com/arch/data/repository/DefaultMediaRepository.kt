package com.arch.data.repository

import com.arch.common.result.Result
import com.arch.database.data_source.WatchMediaLocalDataSource
import com.arch.model.data.WatchMedia
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultMediaRepository @Inject constructor(
    private val localDataSource: WatchMediaLocalDataSource
) : MediaRepository {

    override fun getMyListLatest(): Flow<List<WatchMedia>> = localDataSource.getMyListLatest()

    override fun isMediaAdded(id: Long): Flow<Boolean> = localDataSource.isMediaAdded(id = id)

    override suspend fun addMedia(media: WatchMedia) = localDataSource.addMedia(media = media)

    override suspend fun removeMedia(media: WatchMedia) = localDataSource.removeMedia(media = media)

    override suspend fun getMedia(id: Long): Result<WatchMedia> = localDataSource.getMedia(id = id)

    override fun getMyList(): Flow<List<WatchMedia>> = localDataSource.getMyList()

    override suspend fun searchMyList(query: String): Result<List<WatchMedia>> = localDataSource.searchMyList(query = query)
}