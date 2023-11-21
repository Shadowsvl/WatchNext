package com.arch.data.repository

import com.arch.common.result.Result
import com.arch.model.data.WatchMedia
import com.arch.network.data_source.WatchMediaNetworkDataSource
import javax.inject.Inject

class DefaultMoviesRepository @Inject constructor(
    private val networkDataSource: WatchMediaNetworkDataSource
) : MoviesRepository {

    override suspend fun getCinemaMovies(): Result<List<WatchMedia>> = networkDataSource.getCinemaMovies()

    override suspend fun getLatestMovies(): Result<List<WatchMedia>> = networkDataSource.getLatestMovies()

    override suspend fun getTrendingMovies(): Result<List<WatchMedia>> = networkDataSource.getTrendingMovies()

    override suspend fun getMovie(id: Long): Result<WatchMedia> = networkDataSource.getMovie(id = id)

    override suspend fun getSimilarMovies(id: Long): Result<List<WatchMedia>> = networkDataSource.getSimilarMovies(id = id)

    override suspend fun searchMovies(query: String): Result<List<WatchMedia>> = networkDataSource.searchMovies(query = query)
}