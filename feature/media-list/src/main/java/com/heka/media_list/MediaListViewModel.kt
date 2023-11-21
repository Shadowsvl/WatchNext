package com.heka.media_list

import androidx.annotation.StringRes
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arch.data.repository.InfiniteMediaRepository
import com.arch.data.repository.MediaRepository
import com.arch.model.data.MediaType
import com.arch.ui.R
import com.heka.media_list.navigation.fromLocalArg
import com.heka.media_list.navigation.mediaTypeArg
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MediaListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val mediaRepository: MediaRepository,
    private val infiniteMediaRepository: InfiniteMediaRepository
) : ViewModel() {

    private val mediaType = savedStateHandle.get<MediaType>(mediaTypeArg) ?: MediaType.Movie
    private val fromLocal = savedStateHandle.get<Boolean>(fromLocalArg) ?: false

    @StringRes val screenLabelId = if (fromLocal) R.string.screen_label_my_list else
        when(mediaType) {
            MediaType.Movie -> R.string.screen_label_movies
            MediaType.Tv -> R.string.screen_label_series
        }

    private var _uiState: MutableStateFlow<MediaListUiState> = MutableStateFlow(MediaListUiState.Loading)

    val uiState = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = _uiState.value
    )

    init {
        fetchMediaList()
    }

    fun requestMoreMedia() = viewModelScope.launch {
        if (!fromLocal) infiniteMediaRepository.requestMoreMedia()
    }

    private fun fetchMediaList() = viewModelScope.launch {
        infiniteMediaRepository.clearMedia()

        val source = if (fromLocal) mediaRepository.getMyList() else infiniteMediaRepository.getInfiniteMedia(mediaType)

        source.collectLatest { mediaList ->
            _uiState.update { MediaListUiState.Success(mediaList = mediaList) }
        }
    }
}