package com.heka.watchnext.ui

import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.arch.data.util.NetworkMonitor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberAppState(
    networkMonitor: NetworkMonitor,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
    bottomSheetScaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState()
): AppState {
    return remember(
        networkMonitor,
        coroutineScope,
        navController,
        bottomSheetScaffoldState
    ) {
        AppState(
            navController = navController,
            coroutineScope = coroutineScope,
            bottomSheetScaffoldState = bottomSheetScaffoldState,
            networkMonitor = networkMonitor
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Stable
class AppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
    val bottomSheetScaffoldState: BottomSheetScaffoldState,
    networkMonitor: NetworkMonitor
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val isOffline = networkMonitor.isOnline
        .map(Boolean::not)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false
        )

    fun isBottomSheetVisible() = bottomSheetScaffoldState.bottomSheetState.currentValue == SheetValue.Expanded

    fun showBottomSheet(transitionEnabled: Boolean) {
        coroutineScope.launch {
            if (isBottomSheetVisible() && transitionEnabled) {
                bottomSheetScaffoldState.bottomSheetState.partialExpand()
            }
            bottomSheetScaffoldState.bottomSheetState.expand()
        }
    }

    fun hideBottomSheet() = coroutineScope.launch {
        if (isBottomSheetVisible()) bottomSheetScaffoldState.bottomSheetState.partialExpand()
    }
}