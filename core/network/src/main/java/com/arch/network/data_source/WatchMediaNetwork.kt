package com.arch.network.data_source

import android.content.Context
import com.arch.common.network.AppDispatchers.IO
import com.arch.common.network.Dispatcher
import com.arch.common.result.Result
import com.arch.model.data.WatchMedia
import com.arch.model.exceptions.ApiKeyException
import com.arch.network.ApiResponse
import com.arch.network.BuildConfig
import com.arch.network.HttpCode
import com.arch.network.RetrofitApiFactory
import com.arch.network.asApiResponse
import com.arch.network.tmdb_api.TmdbApi
import com.arch.network.tmdb_api.mapper.asWatchMedia
import com.arch.network.tmdb_api.model.MovieDto
import com.arch.network.tmdb_api.model.TvDto
import com.arch.network.tmdb_api.model.WatchMediaErrorDto
import com.arch.network.util.ClientConfig
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WatchMediaNetwork @Inject constructor(
    @ApplicationContext context: Context,
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
    private val gson: Gson
) : WatchMediaNetworkDataSource {

    private val clientConfig = ClientConfig.Builder(baseUrl = BuildConfig.API_BASE_URL)
        .apiKey(BuildConfig.API_KEY)
        .cacheEnabled(true)
        .build()

    private val client = RetrofitApiFactory(gson).createClient(context, clientConfig)

    private val api = client.getApiService(TmdbApi::class.java)

    override suspend fun getCinemaMovies(): Result<List<WatchMedia>> {
        return api.getCinemaMovies().toResult {
            it.results?.map(MovieDto::asWatchMedia).orEmpty()
        }
    }

    override suspend fun getLatestMovies(page: Int): Result<List<WatchMedia>> {
        return api.getLatestMovies(page = page).toResult {
            it.results?.map(MovieDto::asWatchMedia).orEmpty()
        }
    }

    override suspend fun getTrendingMovies(): Result<List<WatchMedia>> {
        return api.getTrendingMovies().toResult {
            it.results?.map(MovieDto::asWatchMedia).orEmpty()
        }
    }

    override suspend fun getOnAirSeries(): Result<List<WatchMedia>> {
        return api.getOnAirSeries().toResult {
            it.results?.map(TvDto::asWatchMedia).orEmpty()
        }
    }

    override suspend fun getLatestSeries(page: Int): Result<List<WatchMedia>> {
        return api.getLatestSeries(page = page).toResult {
            it.results?.map(TvDto::asWatchMedia).orEmpty()
        }
    }

    override suspend fun getTrendingSeries(): Result<List<WatchMedia>> {
        return api.getTrendingSeries().toResult {
            it.results?.map(TvDto::asWatchMedia).orEmpty()
        }
    }

    override suspend fun getMovie(id: Long): Result<WatchMedia> {
        return api.getMovie(id = id).toResult {
            it.asWatchMedia()
        }
    }

    override suspend fun getTv(id: Long): Result<WatchMedia> {
        return api.getTv(id = id).toResult {
            it.asWatchMedia()
        }
    }

    override suspend fun getSimilarMovies(id: Long): Result<List<WatchMedia>> {
        return api.getSimilarMovies(id = id).toResult {
            it.results?.map(MovieDto::asWatchMedia).orEmpty()
        }
    }

    override suspend fun getSimilarSeries(id: Long): Result<List<WatchMedia>> {
        return api.getSimilarSeries(id = id).toResult {
            it.results?.map(TvDto::asWatchMedia).orEmpty()
        }
    }

    override suspend fun searchMovies(query: String): Result<List<WatchMedia>> {
        return api.searchMovie(search = query).toResult {
            it.results?.map(MovieDto::asWatchMedia).orEmpty()
        }
    }

    override suspend fun searchSeries(query: String): Result<List<WatchMedia>> {
        return api.searchTv(search = query).toResult {
            it.results?.map(TvDto::asWatchMedia).orEmpty()
        }
    }

    private suspend fun <T, R> Response<T>.toResult(dataProcess: (T) -> R): Result<R> = withContext(ioDispatcher) {
        when(val response = asApiResponse(WatchMediaErrorDto::class.java, gson)) {
            is ApiResponse.Success -> response.data?.let { Result.Success(dataProcess(it)) }
                ?: Result.Error(NoSuchElementException("Media data is null"))
            is ApiResponse.HttpError -> Result.Error(response.getApiException())
            is ApiResponse.Failure -> Result.Error(response.exception)
        }
    }
}

private fun ApiResponse.HttpError.getApiException(): Exception {
    val errorDto = errorData as? WatchMediaErrorDto
    return errorDto?.let {
        val message = it.statusMessage ?: this.statusMessage
        when(this.httpCode) {
            HttpCode.Unauthorized -> ApiKeyException(message)
            else -> IOException(message)
        }
    } ?: IOException(statusMessage)
}