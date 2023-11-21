package com.heka.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arch.common.result.Result
import com.arch.data.repository.MediaRepository
import com.heka.home.domain.GetMainSectionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMainSectionsUseCase: GetMainSectionsUseCase,
    mediaRepository: MediaRepository
) : ViewModel() {

    private var _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState.Loading)

    val uiState = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = _uiState.value
    )

    val myListLatest = mediaRepository.getMyListLatest()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    private var _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    init {
        getMainSections()
    }

    fun refreshMainSections() {
        if (_isRefreshing.value) return

        _isRefreshing.value = true
        getMainSections().invokeOnCompletion {
            _isRefreshing.value = false
        }
    }

    private fun getMainSections() = viewModelScope.launch {
        when(val mainSectionsResult = getMainSectionsUseCase()) {
            is Result.Error -> _uiState.update { HomeUiState.AuthError(mainSectionsResult.exception?.message.orEmpty()) }
            is Result.Success -> _uiState.update { HomeUiState.Success(mainSections = mainSectionsResult.data) }
        }
    }
}