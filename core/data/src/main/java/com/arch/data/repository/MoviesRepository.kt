package com.arch.data.repository

import com.arch.common.result.Result
import com.arch.model.data.WatchMedia

interface MoviesRepository {

    suspend fun getCinemaMovies(): Result<List<WatchMedia>>

    suspend fun getLatestMovies(): Result<List<WatchMedia>>

    suspend fun getTrendingMovies(): Result<List<WatchMedia>>

    suspend fun getMovie(id: Long): Result<WatchMedia>

    suspend fun getSimilarMovies(id: Long): Result<List<WatchMedia>>

    suspend fun searchMovies(query: String): Result<List<WatchMedia>>
}