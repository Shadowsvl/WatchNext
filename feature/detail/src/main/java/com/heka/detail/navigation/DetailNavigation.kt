package com.heka.detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.arch.model.data.MediaType
import com.arch.model.data.WatchMedia
import com.heka.detail.DetailRoute

const val mediaIdArg = "mediaId"
const val mediaTypeArg = "mediaType"
const val detailRoute = "detail_route"
internal const val detailFullRoute = "$detailRoute/{$mediaIdArg}/{$mediaTypeArg}"

fun NavController.navigateToDetail(
    watchMedia: WatchMedia,
    navOptions: NavOptions? = null
) {
    this.navigate(
        "$detailRoute/${watchMedia.id}/${watchMedia.mediaType}",
        navOptions
    )
}

fun NavGraphBuilder.detailScreen(
    onBackClick: () -> Unit,
    onPosterClick: (WatchMedia) -> Unit
) {
    composable(
        route = detailFullRoute,
        arguments = listOf(
            navArgument(mediaIdArg) { type = NavType.LongType },
            navArgument(mediaTypeArg) { type = NavType.EnumType(MediaType::class.java) }
        )
    ) {
        DetailRoute(onBackClick = onBackClick, onPosterClick = onPosterClick)
    }
}