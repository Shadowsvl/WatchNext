package com.heka.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.arch.design_system.annotations.ThemePreviews
import com.arch.design_system.component.AppGradientBackground
import com.arch.design_system.theme.AppTheme
import com.arch.design_system.theme.basePadding
import com.arch.model.data.WatchMedia
import com.arch.ui.R
import com.arch.ui.component.DetailBanner
import com.arch.ui.component.MediaDetail
import com.arch.ui.component.MediaSection
import com.arch.ui.component.MediaSectionLoading
import com.arch.ui.component.Overview
import com.arch.ui.component.PosterCarousel
import com.arch.ui.component.PosterCarouselLoading
import com.arch.ui.movieA
import com.arch.ui.watchMediaList

@Composable
fun DetailRoute(
    onBackClick: () -> Unit,
    onPosterClick: (WatchMedia) -> Unit,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val isMediaSaved by viewModel.isMediaSaved.collectAsStateWithLifecycle()

    DetailScreen(
        uiState = uiState,
        isMediaSaved = isMediaSaved,
        onBackClick = onBackClick,
        onMediaAction = viewModel::onMediaAction,
        onPosterClick = onPosterClick
    )
}

@Composable
fun DetailScreen(
    uiState: DetailUiState,
    isMediaSaved: Boolean,
    onBackClick: () -> Unit,
    onMediaAction: (WatchMedia) -> Unit,
    onPosterClick: (WatchMedia) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        when(uiState) {
            DetailUiState.Loading -> {
                Spacer(modifier = Modifier.height(basePadding))
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                MediaSectionLoading(modifier = Modifier.fillMaxWidth()) {
                    PosterCarouselLoading()
                }
            }
            is DetailUiState.Success -> {
                DetailBanner(
                    backdropUrl = uiState.watchMedia?.backdropUrl.orEmpty(),
                    onBackClick = onBackClick
                )
                uiState.watchMedia?.let { watchMedia ->
                    MediaDetail(
                        watchMedia = watchMedia,
                        isSaved = isMediaSaved,
                        onActionClick = onMediaAction,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(basePadding)
                    )
                    if (watchMedia.overview.isNotBlank()) {
                        Overview(
                            overview = watchMedia.overview,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp)
                                .padding(horizontal = basePadding)
                        )
                    }
                }
                if (uiState.similarMedia.isNotEmpty()) {
                    MediaSection(
                        title = stringResource(id = R.string.section_similar_content),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        PosterCarousel(
                            items = uiState.similarMedia,
                            onPosterClick = onPosterClick
                        )
                    }
                }
                Spacer(modifier = Modifier.height(basePadding))
            }
        }
    }
}

@ThemePreviews
@Composable
fun DetailScreenPreview() {
    AppTheme {
        AppGradientBackground {
            DetailScreen(
                uiState = DetailUiState.Success(
                    watchMedia = movieA,
                    similarMedia = watchMediaList
                ),
                isMediaSaved = false,
                onBackClick = {},
                onMediaAction = {},
                onPosterClick = {}
            )
        }
    }
}