package com.arch.database.data_source

import com.arch.common.network.AppDispatchers
import com.arch.common.network.Dispatcher
import com.arch.common.result.Result
import com.arch.database.schema.dao.WatchMediaDao
import com.arch.database.schema.mapper.asWatchMedia
import com.arch.database.schema.mapper.asWatchMediaEntity
import com.arch.database.schema.model.WatchMediaEntity
import com.arch.model.data.WatchMedia
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WatchMediaLocal @Inject constructor(
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val watchMediaDao: WatchMediaDao
) : WatchMediaLocalDataSource {

    override fun getMyListLatest(): Flow<List<WatchMedia>> = watchMediaDao
        .flowLatestMedia()
        .map { it.map(WatchMediaEntity::asWatchMedia) }
        .flowOn(ioDispatcher)

    override fun isMediaAdded(id: Long): Flow<Boolean> = watchMediaDao
        .flowCheckExistsById(id)
        .flowOn(ioDispatcher)

    override suspend fun addMedia(media: WatchMedia) = withContext(ioDispatcher) {
        watchMediaDao.insertMedia(media.asWatchMediaEntity())
    }

    override suspend fun removeMedia(media: WatchMedia) = withContext(ioDispatcher) {
        watchMediaDao.deleteMediaById(media.id)
    }

    override suspend fun getMedia(id: Long): Result<WatchMedia> = withContext(ioDispatcher) {
        watchMediaDao.getMediaById(id)?.asWatchMedia()?.let { Result.Success(it) }
            ?: Result.Error(NoSuchElementException("Media data is null"))
    }

    override fun getMyList(): Flow<List<WatchMedia>> = watchMediaDao
        .flowAllMedia()
        .map { it.map(WatchMediaEntity::asWatchMedia) }
        .flowOn(ioDispatcher)

    override suspend fun searchMyList(query: String): Result<List<WatchMedia>> = withContext(ioDispatcher) {
        watchMediaDao.searchMedia(query).map(WatchMediaEntity::asWatchMedia).let {
            if (it.isEmpty()) Result.Error(IllegalArgumentException("No results for query"))
            else Result.Success(it)
        }
    }
}