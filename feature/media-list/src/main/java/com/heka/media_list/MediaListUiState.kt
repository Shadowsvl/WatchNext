package com.heka.media_list

import com.arch.model.data.WatchMedia

sealed interface MediaListUiState {

    object Loading : MediaListUiState

    data class Success(
        val mediaList: List<WatchMedia>
    ) : MediaListUiState
}