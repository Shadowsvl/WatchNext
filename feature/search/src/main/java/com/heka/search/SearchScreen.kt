package com.heka.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.arch.design_system.annotations.ThemePreviews
import com.arch.design_system.component.AppGradientBackground
import com.arch.design_system.theme.AppTheme
import com.arch.design_system.theme.basePadding
import com.arch.model.data.WatchMedia
import com.arch.ui.R
import com.arch.ui.component.MediaSection
import com.arch.ui.component.MediaSectionLoading
import com.arch.ui.component.PosterCarousel
import com.arch.ui.component.PosterCarouselLoading
import com.arch.ui.component.SearchToolbar
import com.arch.ui.mainSection
import java.util.Locale

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchRoute(
    onBackClick: () -> Unit,
    onPosterClick: (WatchMedia) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val keyboardController = LocalSoftwareKeyboardController.current

    SearchScreen(
        uiState = uiState,
        searchQuery = searchQuery,
        onBackClick = onBackClick,
        onSearchQueryChanged = viewModel::onSearchQueryChanged,
        onPosterClick = {
            keyboardController?.hide()
            onPosterClick(it)
        }
    )
}

@Composable
fun SearchScreen(
    uiState: SearchUiState,
    searchQuery: String,
    onBackClick: () -> Unit,
    onSearchQueryChanged: (String) -> Unit,
    onPosterClick: (WatchMedia) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(100.dp))
            when(uiState) {
                SearchUiState.Loading -> {
                    List(2) {
                        MediaSectionLoading(modifier = Modifier.fillMaxWidth()) {
                            PosterCarouselLoading()
                        }
                    }
                }
                is SearchUiState.Success -> {
                    uiState.resultSections.forEach { section ->
                        MediaSection(
                            title = stringResource(id = section.titleId),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            PosterCarousel(
                                items = section.watchMediaList,
                                onPosterClick = onPosterClick
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(basePadding))
                }
            }
        }
        (uiState as? SearchUiState.Success)?.let {
            if (uiState.resultSections.isEmpty()) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(basePadding),
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_watch_next_icon),
                        contentDescription = "WatchNextIcon",
                        modifier = Modifier.size(100.dp)
                    )
                    if (searchQuery.isNotBlank()) {
                        Text(
                            text = stringResource(id = R.string.notify_search_empty)
                                .uppercase(Locale.getDefault()),
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                }
            }
        }
        SearchToolbar(
            searchQuery = searchQuery,
            onBackClick = onBackClick,
            onSearchQueryChanged = onSearchQueryChanged
        )
    }
}

@ThemePreviews
@Composable
fun SearchScreenPreview() {
    AppTheme {
        AppGradientBackground {
            SearchScreen(
                uiState = SearchUiState.Success(resultSections = listOf(mainSection)),
                searchQuery = "",
                onBackClick = {},
                onSearchQueryChanged = {},
                onPosterClick = {}
            )
        }
    }
}