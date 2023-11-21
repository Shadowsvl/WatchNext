package com.heka.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.arch.model.data.WatchMedia
import com.heka.search.SearchRoute

const val searchRoute = "search_route"

fun NavController.navigateToSearch(navOptions: NavOptions? = null) {
    this.navigate(searchRoute, navOptions)
}

fun NavGraphBuilder.searchScreen(
    onBackClick: () -> Unit,
    onPosterClick: (WatchMedia) -> Unit
) {
    composable(route = searchRoute) {
        SearchRoute(
            onBackClick = onBackClick,
            onPosterClick = onPosterClick
        )
    }
}