package com.heka.detail

import com.arch.model.data.WatchMedia

sealed interface DetailUiState {

    object Loading : DetailUiState

    data class Success(
        val watchMedia: WatchMedia?,
        val similarMedia: List<WatchMedia>
    ) : DetailUiState
}