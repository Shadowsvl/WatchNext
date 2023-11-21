package com.heka.watchnext.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.arch.model.data.MediaType
import com.arch.model.data.WatchMedia
import com.heka.detail.navigation.detailScreen
import com.heka.home.navigation.homeRoute
import com.heka.home.navigation.homeScreen
import com.heka.media_list.navigation.mediaListScreen
import com.heka.media_list.navigation.navigateToMediaList
import com.heka.search.navigation.navigateToSearch
import com.heka.search.navigation.searchScreen
import com.heka.watchnext.ui.AppState

@Composable
fun AppNavHost(
    appState: AppState,
    modifier: Modifier = Modifier,
    startDestination: String = homeRoute,
    onPosterClick: (WatchMedia) -> Unit
) {
    val navController = appState.navController

    BackHandler(enabled = appState.isBottomSheetVisible()) {
        appState.hideBottomSheet()
    }

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        homeScreen(
            onMoviesClick = {
                appState.hideBottomSheet()
                navController.navigateToMediaList(mediaType = MediaType.Movie) },
            onSeriesClick = {
                appState.hideBottomSheet()
                navController.navigateToMediaList(mediaType = MediaType.Tv) },
            onMyListClick = {
                appState.hideBottomSheet()
                navController.navigateToMediaList(fromLocal = true) },
            onSearchClick = {
                appState.hideBottomSheet()
                navController.navigateToSearch() },
            onPosterClick = onPosterClick
        )
        detailScreen(
            onBackClick = {
                appState.hideBottomSheet()
                navController.navigateUp() },
            onPosterClick = onPosterClick
        )
        mediaListScreen(
            onBackClick = {
                appState.hideBottomSheet()
                navController.navigateUp() },
            onSearchClick = {
                appState.hideBottomSheet()
                navController.navigateToSearch() },
            onPosterClick = onPosterClick
        )
        searchScreen(
            onBackClick = {
                appState.hideBottomSheet()
                navController.navigateUp() },
            onPosterClick = onPosterClick
        )
    }
}