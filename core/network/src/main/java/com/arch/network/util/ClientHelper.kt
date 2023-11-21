package com.arch.network.util

import android.content.Context
import com.arch.network.BuildConfig
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File

private const val API_KEY = "api_key"

fun clientFromConfig(context: Context, clientConfig: ClientConfig): OkHttpClient {
    val builder = OkHttpClient.Builder()

    val cacheControlBuilder = CacheControl.Builder()
    val cacheControl = cacheControlBuilder.apply {
        if (clientConfig.cacheEnabled) {
            maxAge(clientConfig.cacheMaxAge.timeout.toInt(), clientConfig.cacheMaxAge.unit)
        } else {
            noCache()
        }
    }.build()

    builder.apply {
        connectTimeout(clientConfig.connectTimeout.timeout, clientConfig.connectTimeout.unit)
        readTimeout(clientConfig.readTimeout.timeout, clientConfig.readTimeout.unit)
        writeTimeout(clientConfig.writeTimeout.timeout, clientConfig.writeTimeout.unit)

        if (BuildConfig.DEBUG) {
            addInterceptor(OkHttpProfilerInterceptor())
        }

        clientConfig.apiKey?.let {
            addInterceptor { chain ->
                val originalRequest = chain.request()
                val originalHttpUrl = originalRequest.url

                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter(API_KEY, it)
                    .build()
                val requestBuilder = originalRequest.newBuilder()
                    .url(url)

                val request = requestBuilder.build()
                return@addInterceptor chain.proceed(request)
            }
        }

        addInterceptor { chain ->
            val requestBuilder = chain
                .request()
                .newBuilder()

            requestBuilder.cacheControl(cacheControl)

            chain.proceed(requestBuilder.build())
        }

        addNetworkInterceptor { chain ->
            val requestBuilder = chain
                .proceed(chain.request())
                .newBuilder()

            requestBuilder.header("Cache-Control", cacheControl.toString())

            requestBuilder.build()
        }

        if (clientConfig.cacheEnabled) {
            cache(
                Cache(
                    directory = File(context.cacheDir, "http_cache"),
                    maxSize = clientConfig.cacheSize
                )
            )
        }

        addInterceptor(
            HttpLoggingInterceptor()
                .apply {
                    if (BuildConfig.DEBUG) {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    }
                }
        )
    }

    return builder.build()
}