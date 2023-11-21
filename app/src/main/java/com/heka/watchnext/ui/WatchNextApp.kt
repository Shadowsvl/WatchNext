package com.heka.watchnext.ui

import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.arch.data.util.NetworkMonitor
import com.arch.design_system.component.AppGradientBackground
import com.arch.ui.R
import com.arch.ui.component.DetailSheet
import com.heka.detail.navigation.navigateToDetail
import com.heka.watchnext.navigation.AppNavHost

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WatchNextApp(
    networkMonitor: NetworkMonitor,
    appState: AppState = rememberAppState(
        networkMonitor = networkMonitor
    ),
    viewModel: AppViewModel = hiltViewModel()
) {
    AppGradientBackground {
        val snackbarHostState = remember { SnackbarHostState() }

        val isOffline by appState.isOffline.collectAsStateWithLifecycle()
        val notConnectedMessage = stringResource(id = R.string.notify_not_connected)

        val currentWatchMedia by viewModel.currentWatchMedia.collectAsStateWithLifecycle()
        val isMediaSaved by viewModel.isMediaSaved.collectAsStateWithLifecycle()

        LaunchedEffect(isOffline) {
            if (isOffline) {
                snackbarHostState.showSnackbar(
                    message = notConnectedMessage,
                    duration = SnackbarDuration.Indefinite
                )
            }
        }

        BottomSheetScaffold(
            containerColor = Color.Transparent,
            sheetContainerColor = MaterialTheme.colorScheme.surface,
            scaffoldState = appState.bottomSheetScaffoldState,
            sheetPeekHeight = 0.dp,
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
            sheetContent = {
                currentWatchMedia?.let {
                    DetailSheet(
                        watchMedia = it,
                        isSaved = isMediaSaved,
                        onActionClick = viewModel::onMediaAction,
                        onDetailClick = { media ->
                            appState.hideBottomSheet()
                            appState.navController.navigateToDetail(media)
                        }
                    )
                }
            },
            modifier = Modifier.imePadding()
        ) { padding ->

            AppNavHost(
                appState = appState,
                modifier = Modifier.padding(padding),
                onPosterClick = {
                    val isDiffMedia = currentWatchMedia != it

                    if (isDiffMedia) viewModel.updateCurrentWatchMedia(it)

                    appState.showBottomSheet(transitionEnabled = isDiffMedia)
                }
            )
        }
    }
}