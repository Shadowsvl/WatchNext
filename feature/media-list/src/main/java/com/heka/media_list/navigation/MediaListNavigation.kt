package com.heka.media_list.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.arch.model.data.MediaType
import com.arch.model.data.WatchMedia
import com.heka.media_list.MediaListRoute

const val mediaTypeArg = "mediaType"
const val fromLocalArg = "fromLocal"
const val mediaListRoute = "media_list_route"
internal const val mediaListFullRoute = "$mediaListRoute/{$mediaTypeArg}/{$fromLocalArg}"

fun NavController.navigateToMediaList(
    mediaType: MediaType = MediaType.Movie,
    fromLocal: Boolean = false,
    navOptions: NavOptions? = null
) {
    this.navigate(
        "$mediaListRoute/$mediaType/$fromLocal",
        navOptions
    )
}

fun NavGraphBuilder.mediaListScreen(
    onBackClick: () -> Unit,
    onSearchClick: () -> Unit,
    onPosterClick: (WatchMedia) -> Unit
) {
    composable(
        route = mediaListFullRoute,
        arguments = listOf(
            navArgument(mediaTypeArg) { type = NavType.EnumType(MediaType::class.java) },
            navArgument(fromLocalArg) { type = NavType.BoolType }
        )
    ) {
        MediaListRoute(
            onBackClick = onBackClick,
            onSearchClick = onSearchClick,
            onPosterClick = onPosterClick
        )
    }
}