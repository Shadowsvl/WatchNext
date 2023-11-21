package com.heka.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.arch.model.data.WatchMedia
import com.heka.home.HomeRoute

const val homeRoute = "home_route"

fun NavGraphBuilder.homeScreen(
    onMoviesClick: () -> Unit,
    onSeriesClick: () -> Unit,
    onMyListClick: () -> Unit,
    onSearchClick: () -> Unit,
    onPosterClick: (WatchMedia) -> Unit
) {
    composable(route = homeRoute) {
        HomeRoute(
            onMoviesClick = onMoviesClick,
            onSeriesClick = onSeriesClick,
            onMyListClick = onMyListClick,
            onSearchClick = onSearchClick,
            onPosterClick = onPosterClick
        )
    }
}