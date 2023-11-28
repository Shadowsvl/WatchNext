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
            async { moviesRepository.getCinemaMovies().toMainSectionResult(R.string.section_cinema_movies) },
            async { moviesRepository.getLatestMovies().toMainSectionResult(R.string.section_latest_movies, SectionType.Banner) },
            async { moviesRepository.getTrendingMovies().toMainSectionResult(R.string.section_trending_movies) },
            async { seriesRepository.getOnAirSeries().toMainSectionResult(R.string.section_on_air_series) },
            async { seriesRepository.getLatestSeries().toMainSectionResult(R.string.section_latest_series, SectionType.Banner) },
            async { seriesRepository.getTrendingSeries().toMainSectionResult(R.string.section_trending_series) }
        )

        val mainSections = mutableListOf<MainSection>()

        requests.awaitAll().forEach {
            when(it) {
                is Result.Error -> return@coroutineScope Result.Error(it.exception)
                is Result.Success -> mainSections.add(it.data)
            }
        }

        Result.Success(mainSections.toList().filter { it.watchMediaList.isNotEmpty() })
    }

    private fun Result<List<WatchMedia>>.toMainSectionResult(
        @StringRes titleId: Int,
        sectionType: SectionType = SectionType.Poster
    ): Result<MainSection> {
        return when(this) {
            is Result.Error -> {
                if (exception is ApiKeyException) {
                    Result.Error(this.exception)
                } else {
                    Result.Success(MainSection(titleId, emptyList()))
                }
            }
            is Result.Success -> Result.Success(MainSection(titleId, data, sectionType))
        }
    }
}