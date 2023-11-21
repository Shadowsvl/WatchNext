package com.heka.media_list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.arch.design_system.annotations.ThemePreviews
import com.arch.design_system.component.AppGradientBackground
import com.arch.design_system.icon.AppIcons
import com.arch.design_system.theme.AppTheme
import com.arch.design_system.theme.basePadding
import com.arch.model.data.WatchMedia
import com.arch.ui.MediaContentTopSpace
import com.arch.ui.component.PosterGrid
import com.arch.ui.component.PosterGridLoading
import com.arch.ui.component.ScreenTopBar
import com.arch.ui.isCloseToBottom
import com.arch.ui.watchMediaList
import kotlinx.coroutines.launch

@Composable
fun MediaListRoute(
    onBackClick: () -> Unit,
    onSearchClick: () -> Unit,
    onPosterClick: (WatchMedia) -> Unit,
    viewModel: MediaListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val gridState = rememberLazyGridState()

    if (uiState is MediaListUiState.Success) {
        val isGridCloseToBottom by remember {
            derivedStateOf {
                (uiState as MediaListUiState.Success).mediaList.isNotEmpty() && gridState.isCloseToBottom()
            }
        }

        LaunchedEffect(isGridCloseToBottom) {
            if (isGridCloseToBottom) viewModel.requestMoreMedia()
        }
    }

    MediaListScreen(
        screenLabel = stringResource(id = viewModel.screenLabelId),
        uiState = uiState,
        gridState = gridState,
        onBackClick = onBackClick,
        onSearchClick = onSearchClick,
        onPosterClick = onPosterClick
    )
}

@Composable
fun MediaListScreen(
    screenLabel: String,
    uiState: MediaListUiState,
    gridState: LazyGridState,
    onBackClick: () -> Unit,
    onSearchClick: () -> Unit,
    onPosterClick: (WatchMedia) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when(uiState) {
            MediaListUiState.Loading -> {
                PosterGridLoading(
                    paddingValues = PaddingValues(
                        top = MediaContentTopSpace,
                        start = basePadding,
                        end = basePadding,
                        bottom = basePadding
                    )
                )
            }
            is MediaListUiState.Success -> {
                val coroutineScope = rememberCoroutineScope()
                val showUpButton by remember {
                    derivedStateOf {
                        gridState.firstVisibleItemIndex > 8
                    }
                }

                PosterGrid(
                    items = uiState.mediaList,
                    modifier = Modifier.fillMaxSize(),
                    paddingValues = PaddingValues(
                        top = MediaContentTopSpace,
                        start = basePadding,
                        end = basePadding,
                        bottom = basePadding
                    ),
                    gridState = gridState,
                    onPosterClick = onPosterClick
                )

                ScreenTopBar(
                    screenLabel = screenLabel,
                    onBackClick = onBackClick,
                    onSearchClick = onSearchClick
                )

                AnimatedVisibility(
                    modifier = Modifier.align(Alignment.BottomEnd),
                    visible = showUpButton
                ) {
                    FloatingActionButton(
                        shape = CircleShape,
                        modifier = Modifier
                            .padding(basePadding),
                        onClick = {
                            coroutineScope.launch { gridState.scrollToItem(0) }
                        }
                    ) {
                        Icon(
                            imageVector = AppIcons.ArrowUpward,
                            contentDescription = "ArrowUpward"
                        )
                    }
                }
            }
        }
    }
}

@ThemePreviews
@Composable
fun MediaListScreenPreview() {
    AppTheme {
        AppGradientBackground {
            MediaListScreen(
                screenLabel = "Películas",
                uiState = MediaListUiState.Success(
                    mediaList = watchMediaList
                ),
                gridState = rememberLazyGridState(),
                onBackClick = {},
                onSearchClick = {},
                onPosterClick = {}
            )
        }
    }
}