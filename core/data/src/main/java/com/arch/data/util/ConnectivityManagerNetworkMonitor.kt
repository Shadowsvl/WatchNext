package com.arch.data.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest.Builder
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import androidx.core.content.getSystemService
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate
import javax.inject.Inject

class ConnectivityManagerNetworkMonitor @Inject constructor(
    @ApplicationContext private val context: Context
) : NetworkMonitor {

    override val isOnline: Flow<Boolean> = callbackFlow {

        val connectivityManager = context.getSystemService<ConnectivityManager>()
        if (connectivityManager == null) {
            channel.trySend(false)
            channel.close()
            return@callbackFlow
        }

        fun update() {
            channel.trySend(connectivityManager.isCurrentlyConnected())
        }

        val callback = object : NetworkCallback() {
            override fun onAvailable(network: Network) = update()

            override fun onLost(network: Network) = update()

            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities
            ) = update()
        }

        connectivityManager.registerNetworkCallback(
            Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .build(),
            callback
        )

        update()

        awaitClose {
            connectivityManager.unregisterNetworkCallback(callback)
        }
    }
        .conflate()

    @Suppress("DEPRECATION")
    private fun ConnectivityManager.isCurrentlyConnected() = when {
        VERSION.SDK_INT >= VERSION_CODES.M ->
            activeNetwork
                ?.let(::getNetworkCapabilities)
                ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        else -> activeNetworkInfo?.isConnected
    } ?: false
}
