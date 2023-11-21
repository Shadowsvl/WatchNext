package com.arch.data.repository

import com.arch.common.result.Result
import com.arch.model.data.WatchMedia

interface SeriesRepository {

    suspend fun getOnAirSeries(): Result<List<WatchMedia>>

    suspend fun getLatestSeries(): Result<List<WatchMedia>>

    suspend fun getTrendingSeries(): Result<List<WatchMedia>>

    suspend fun getTv(id: Long): Result<WatchMedia>

    suspend fun getSimilarSeries(id: Long): Result<List<WatchMedia>>

    suspend fun searchSeries(query: String): Result<List<WatchMedia>>
}