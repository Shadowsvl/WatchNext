package com.heka.search

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arch.common.result.Result
import com.arch.data.repository.MediaRepository
import com.arch.data.repository.MoviesRepository
import com.arch.data.repository.SeriesRepository
import com.arch.model.data.MainSection
import com.arch.model.data.WatchMedia
import com.arch.ui.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val seriesRepository: SeriesRepository,
    private val mediaRepository: MediaRepository
) : ViewModel() {

    private var _uiState: MutableStateFlow<SearchUiState> = MutableStateFlow(SearchUiState.Success(emptyList()))

    val uiState = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = _uiState.value
    )

    private var _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private var searchJob: Job? = null

    fun onSearchQueryChanged(query: String) {
        _searchQuery.update { query }
        searchJob?.cancel()
        searchJob = getResultSections(query)
    }

    private fun getResultSections(query: String) = viewModelScope.launch {
        if (query.isBlank()) {
            _uiState.update { SearchUiState.Success(emptyList()) }
            return@launch
        }

        delay(300)

        _uiState.update { SearchUiState.Loading }

        val requests = listOf(
            async { moviesRepository.searchMovies(query).toResultSection(R.string.section_search_result_movies) },
            async { seriesRepository.searchSeries(query).toResultSection(R.string.section_search_result_series) },
            async { mediaRepository.searchMyList(query).toResultSection(R.string.section_search_result_my_list) }
        )

        val resultSections = requests.awaitAll().filter { it.watchMediaList.isNotEmpty() }

        _uiState.update { SearchUiState.Success(resultSections = resultSections) }
    }

    private fun Result<List<WatchMedia>>.toResultSection(
        @StringRes titleId: Int
    ) = MainSection(
        titleId = titleId,
        watchMediaList = when(this) {
            is Result.Error -> emptyList()
            is Result.Success -> data
        }
    )
}