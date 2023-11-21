package com.heka.home

import com.arch.model.data.MainSection

sealed interface HomeUiState {

    object Loading : HomeUiState

    data class AuthError(val message: String) : HomeUiState

    data class Success(
        val mainSections: List<MainSection>
    ) : HomeUiState
}