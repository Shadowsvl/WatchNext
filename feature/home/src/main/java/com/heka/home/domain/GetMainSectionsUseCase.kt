package com.heka.home.domain

import androidx.annotation.StringRes
import com.arch.common.result.Result
import com.arch.data.repository.MoviesRepository
import com.arch.data.repository.SeriesRepository
import com.arch.model.data.MainSection
import com.arch.model.data.SectionType
import com.arch.model.data.WatchMedia
import com.arch.model.exceptions.ApiKeyException
import com.arch.ui.R
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class GetMainSectionsUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val seriesRepository: SeriesRepository
) {

    suspend operator fun invoke(): Result<List<MainSection>> = coroutineScope {
        val requests = listOf(
            async { moviesRepository.getCinemaMovies().toMainSection(R.string.section_cinema_movies) },
            async { moviesRepository.getLatestMovies().toMainSection(R.string.section_latest_movies, SectionType.Banner) },
            async { moviesRepository.getTrendingMovies().toMainSection(R.string.section_trending_movies) },
            async { seriesRepository.getOnAirSeries().toMainSection(R.string.section_on_air_series) },
            async { seriesRepository.getLatestSeries().toMainSection(R.string.section_latest_series, SectionType.Banner) },
            async { seriesRepository.getTrendingSeries().toMainSection(R.string.section_trending_series) }
        )
        try {
            val mainSections = requests.awaitAll().filter { it.watchMediaList.isNotEmpty() }
            Result.Success(mainSections)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    private fun Result<List<WatchMedia>>.toMainSection(
        @StringRes titleId: Int,
        sectionType: SectionType = SectionType.Poster
    ): MainSection {
        return when(this) {
            is Result.Error -> {
                if (exception is ApiKeyException) {
                    throw ApiKeyException(exception?.message.orEmpty())
                } else {
                    MainSection(titleId, emptyList())
                }
            }
            is Result.Success -> MainSection(titleId, data, sectionType)
        }
    }
}