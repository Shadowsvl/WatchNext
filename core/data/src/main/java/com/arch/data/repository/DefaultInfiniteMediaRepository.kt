package com.arch.data.repository

import com.arch.common.result.getDataOr
import com.arch.model.data.MediaType
import com.arch.model.data.WatchMedia
import com.arch.network.data_source.WatchMediaNetworkDataSource
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import java.util.concurrent.CopyOnWriteArrayList
import javax.inject.Inject

class DefaultInfiniteMediaRepository @Inject constructor(
    private val networkDataSource: WatchMediaNetworkDataSource
) : InfiniteMediaRepository {

    private val requestSize = 4
    private val pageState = MutableStateFlow(0)
    private val infiniteList = CopyOnWriteArrayList<WatchMedia>()

    override fun getInfiniteMedia(mediaType: MediaType): Flow<List<WatchMedia>> = pageState.map { page ->
        val mediaList = coroutineScope {
            val requests = List(requestSize) {
                async { getMediaPageByMediaType(page = page + (it + 1), mediaType = mediaType) }
            }
            requests.awaitAll().flatten()
        }
        synchronized(infiniteList) {
            if (!infiniteList.contains(mediaList.firstOrNull())) {
                infiniteList.addAll(mediaList)
            }
            infiniteList.toList()
        }
    }.catch { emit(infiniteList.toList()) }

    override suspend fun requestMoreMedia() {
        pageState.update { it.plus(requestSize) }
    }

    override suspend fun clearMedia() {
        pageState.update { 0 }
        synchronized(infiniteList) {
            infiniteList.clear()
        }
    }

    private suspend fun getMediaPageByMediaType(page: Int, mediaType: MediaType): List<WatchMedia> = when(mediaType) {
        MediaType.Movie -> networkDataSource.getLatestMovies(page = page).getDataOr(emptyList())
        MediaType.Tv -> networkDataSource.getLatestSeries(page = page).getDataOr(emptyList())
    }
}