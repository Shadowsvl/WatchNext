package com.arch.data.repository

import com.arch.common.result.Result
import com.arch.model.data.WatchMedia
import com.arch.network.data_source.WatchMediaNetworkDataSource
import javax.inject.Inject

class DefaultSeriesRepository @Inject constructor(
    private val networkDataSource: WatchMediaNetworkDataSource
) : SeriesRepository {

    override suspend fun getOnAirSeries(): Result<List<WatchMedia>> = networkDataSource.getOnAirSeries()

    override suspend fun getLatestSeries(): Result<List<WatchMedia>> = networkDataSource.getLatestSeries()

    override suspend fun getTrendingSeries(): Result<List<WatchMedia>> = networkDataSource.getTrendingSeries()

    override suspend fun getTv(id: Long): Result<WatchMedia> = networkDataSource.getTv(id = id)

    override suspend fun getSimilarSeries(id: Long): Result<List<WatchMedia>> = networkDataSource.getSimilarSeries(id = id)

    override suspend fun searchSeries(query: String): Result<List<WatchMedia>> = networkDataSource.searchSeries(query = query)
}