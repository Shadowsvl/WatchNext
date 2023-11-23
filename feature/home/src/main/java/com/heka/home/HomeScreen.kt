package com.heka.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.arch.model.data.SectionType
import com.arch.model.data.WatchMedia
import com.arch.ui.MediaContentTopSpace
import com.arch.ui.R
import com.arch.ui.component.ApiKeyError
import com.arch.ui.component.BannerCarousel
import com.arch.ui.component.BannerCarouselLoading
import com.arch.ui.component.HomeTopBar
import com.arch.ui.component.MediaSection
import com.arch.ui.component.MediaSectionLoading
import com.arch.ui.component.PosterCarousel
import com.arch.ui.component.PosterCarouselLoading
import com.arch.ui.mainSection
import com.arch.ui.watchMediaList
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
internal fun HomeRoute(
    onMoviesClick: () -> Unit,
    onSeriesClick: () -> Unit,
    onMyListClick: () -> Unit,
    onSearchClick: () -> Unit,
    onPosterClick: (WatchMedia) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val myListLatest by viewModel.myListLatest.collectAsStateWithLifecycle()
    val isRefreshing by viewModel.isRefreshing.collectAsStateWithLifecycle()

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
        indicator = { state, refreshTrigger ->
            SwipeRefreshIndicator(
                state = state,
                refreshTriggerDistance = refreshTrigger,
                backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
            )
        },
        onRefresh = viewModel::refreshMainSections
    ) {
        HomeScreen(
            uiState = uiState,
            myListLatest = myListLatest,
            onMoviesClick = onMoviesClick,
            onSeriesClick = onSeriesClick,
            onMyListClick = onMyListClick,
            onSearchClick = onSearchClick,
            onPosterClick = onPosterClick
        )
    }
}

@Composable
internal fun HomeScreen(
    uiState: HomeUiState,
    myListLatest: List<WatchMedia>,
    onMoviesClick: () -> Unit,
    onSeriesClick: () -> Unit,
    onMyListClick: () -> Unit,
    onSearchClick: () -> Unit,
    onPosterClick: (WatchMedia) -> Unit
) {
    val listState = rememberLazyListState()

    LaunchedEffect(myListLatest) {
        listState.animateScrollToItem(0)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(135.dp))
            AnimatedVisibility(visible = myListLatest.isNotEmpty()) {
                MediaSection(
                    title = stringResource(id = R.string.section_my_list_latest),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    PosterCarousel(
                        items = myListLatest,
                        listState = listState,
                        onPosterClick = onPosterClick
                    )
                }
            }
            MainSections(
                uiState = uiState,
                onPosterClick = onPosterClick
            )
            Spacer(modifier = Modifier.height(basePadding))
        }
        HomeTopBar(
            myListEnabled = myListLatest.isNotEmpty(),
            onMoviesClick = onMoviesClick,
            onSeriesClick = onSeriesClick,
            onMyListClick = onMyListClick,
            onSearchClick = onSearchClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun MainSections(
    uiState: HomeUiState,
    onPosterClick: (WatchMedia) -> Unit
) {
    when(uiState) {
        HomeUiState.Loading -> {
            MediaSectionLoading(modifier = Modifier.fillMaxWidth()) {
                PosterCarouselLoading()
            }
            MediaSectionLoading(modifier = Modifier.fillMaxWidth()) {
                BannerCarouselLoading()
            }
            List(2) {
                MediaSectionLoading(modifier = Modifier.fillMaxWidth()) {
                    PosterCarouselLoading()
                }
            }
        }
        is HomeUiState.AuthError -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = MediaContentTopSpace)
            ) {
                ApiKeyError(message = uiState.message)
            }
        }
        is HomeUiState.Success -> {
            uiState.mainSections.forEach { section ->
                MediaSection(
                    title = stringResource(id = section.titleId),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    when(section.sectionType) {
                        SectionType.Poster -> {
                            PosterCarousel(
                                items = section.watchMediaList,
                                onPosterClick = onPosterClick,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        SectionType.Banner -> {
                            BannerCarousel(
                                items = section.watchMediaList,
                                onBannerClick = onPosterClick,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }
        }
    }
}

@ThemePreviews
@Composable
fun HomeScreenPreview() {
    AppTheme {
        AppGradientBackground {
            HomeScreen(
                uiState = HomeUiState.Success(mainSections = listOf(mainSection)),
                myListLatest = watchMediaList,
                onMoviesClick = {},
                onSeriesClick = {},
                onMyListClick = {},
                onSearchClick = {},
                onPosterClick = {}
            )
        }
    }
}