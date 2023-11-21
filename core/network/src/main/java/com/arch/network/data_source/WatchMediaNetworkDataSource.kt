package com.arch.network.data_source

import com.arch.common.result.Result
import com.arch.model.data.WatchMedia

interface WatchMediaNetworkDataSource {

    suspend fun getCinemaMovies(): Result<List<WatchMedia>>

    suspend fun getLatestMovies(page: Int = 1): Result<List<WatchMedia>>

    suspend fun getTrendingMovies(): Result<List<WatchMedia>>

    suspend fun getOnAirSeries(): Result<List<WatchMedia>>

    suspend fun getLatestSeries(page: Int = 1): Result<List<WatchMedia>>

    suspend fun getTrendingSeries(): Result<List<WatchMedia>>

    suspend fun getMovie(id: Long): Result<WatchMedia>

    suspend fun getTv(id: Long): Result<WatchMedia>

    suspend fun getSimilarMovies(id: Long): Result<List<WatchMedia>>

    suspend fun getSimilarSeries(id: Long): Result<List<WatchMedia>>

    suspend fun searchMovies(query: String): Result<List<WatchMedia>>

    suspend fun searchSeries(query: String): Result<List<WatchMedia>>
}