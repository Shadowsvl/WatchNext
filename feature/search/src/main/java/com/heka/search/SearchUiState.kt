package com.heka.search

import com.arch.model.data.MainSection

sealed interface SearchUiState {

    object Loading : SearchUiState

    data class Success(
        val resultSections: List<MainSection>
    ) : SearchUiState
}