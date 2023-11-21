package com.heka.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arch.common.result.Result
import com.arch.common.result.getDataOr
import com.arch.common.result.getDataOrNull
import com.arch.data.repository.MediaRepository
import com.arch.data.repository.MoviesRepository
import com.arch.data.repository.SeriesRepository
import com.arch.model.data.MediaType
import com.arch.model.data.WatchMedia
import com.heka.detail.navigation.mediaIdArg
import com.heka.detail.navigation.mediaTypeArg
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val moviesRepository: MoviesRepository,
    private val seriesRepository: SeriesRepository,
    private val mediaRepository: MediaRepository
) : ViewModel() {

    private val mediaId = savedStateHandle.get<Long>(mediaIdArg) ?: 0L
    private val mediaType = savedStateHandle.get<MediaType>(mediaTypeArg) ?: MediaType.Movie

    private var _uiState: MutableStateFlow<DetailUiState> = MutableStateFlow(DetailUiState.Loading)

    val uiState = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = _uiState.value
    )

    private val _isMediaSaved = MutableStateFlow(false)
    val isMediaSaved = _isMediaSaved.asStateFlow()

    init {
        fetchDetail()

        viewModelScope.launch {
            mediaRepository.isMediaAdded(mediaId).collectLatest { isAdded ->
                _isMediaSaved.update { isAdded }
            }
        }
    }

    private fun fetchDetail() = viewModelScope.launch {
        val watchMedia = async {
            when(val result = mediaRepository.getMedia(mediaId)) {
                is Result.Error -> when(mediaType) {
                    MediaType.Movie -> moviesRepository.getMovie(mediaId)
                    MediaType.Tv -> seriesRepository.getTv(mediaId)
                }.getDataOrNull()
                is Result.Success -> result.data
            }
        }
        val similarMediaList = async {
            when(mediaType) {
                MediaType.Movie -> moviesRepository.getSimilarMovies(mediaId)
                MediaType.Tv -> seriesRepository.getSimilarSeries(mediaId)
            }.getDataOr(emptyList())
        }

        _uiState.update {
            DetailUiState.Success(
                watchMedia = watchMedia.await(),
                similarMedia = similarMediaList.await()
            )
        }
    }

    fun onMediaAction(watchMedia: WatchMedia) {
        viewModelScope.launch {
            if (_isMediaSaved.value) {
                mediaRepository.removeMedia(watchMedia)
            } else {
                mediaRepository.addMedia(watchMedia)
            }
        }
    }
}