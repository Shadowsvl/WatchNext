package com.arch.network

import android.content.Context
import com.arch.network.util.ClientConfig

interface NetworkClientFactory {
    fun createClient(context: Context, clientConfig: ClientConfig): NetworkClient
}