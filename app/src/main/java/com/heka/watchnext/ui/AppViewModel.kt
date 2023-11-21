package com.heka.watchnext.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arch.data.repository.MediaRepository
import com.arch.model.data.WatchMedia
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val mediaRepository: MediaRepository
) : ViewModel() {

    private val _currentWatchMedia: MutableStateFlow<WatchMedia?> = MutableStateFlow(null)
    val currentWatchMedia = _currentWatchMedia.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = _currentWatchMedia.value
    )

    private val _isMediaSaved = MutableStateFlow(false)
    val isMediaSaved = _isMediaSaved.asStateFlow()

    private var isMediaSavedJob: Job? = null

    fun updateCurrentWatchMedia(watchMedia: WatchMedia) {
        _currentWatchMedia.update { watchMedia }
        isMediaSavedJob?.cancel()
        isMediaSavedJob = viewModelScope.launch {
            mediaRepository.isMediaAdded(watchMedia.id).collectLatest { isAdded ->
                _isMediaSaved.update { isAdded }
            }
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