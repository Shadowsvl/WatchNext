package com.arch.network

import android.content.Context
import com.arch.network.util.ClientConfig
import com.arch.network.util.clientFromConfig
import com.google.gson.Gson

class RetrofitApiFactory(
    private val gson: Gson
) : NetworkClientFactory {

    override fun createClient(context: Context, clientConfig: ClientConfig): NetworkClient {
        return RetrofitClient(
            baseUrl = clientConfig.baseUrl,
            gson = gson,
            okHttpClient = clientFromConfig(context, clientConfig)
        )
    }
}