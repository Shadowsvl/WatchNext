package com.arch.network.util

import java.util.concurrent.TimeUnit

class ClientConfig private constructor(
    val baseUrl: String,
    val readTimeout: Timeout,
    val writeTimeout: Timeout,
    val connectTimeout: Timeout,
    val cacheEnabled: Boolean,
    val cacheMaxAge: Timeout,
    val cacheSize: Long,
    val apiKey: String?
) {

    companion object {
        private val DEFAULT_TIMEOUT = Timeout(10L, TimeUnit.SECONDS)
        private val DEFAULT_CACHE_MAX_AGE = Timeout(2L, TimeUnit.MINUTES)
        private const val DEFAULT_CACHE_SIZE = 50L * 1024L * 1024L // 50 MiB
    }

    data class Builder(
        val baseUrl: String,
        private var _readTimeout: Timeout = DEFAULT_TIMEOUT,
        private var _writeTimeout: Timeout = DEFAULT_TIMEOUT,
        private var _connectTimeout: Timeout = DEFAULT_TIMEOUT,
        private var _cacheEnabled: Boolean = false,
        private var _cacheMaxAge: Timeout = DEFAULT_CACHE_MAX_AGE,
        private var _cacheSize: Long = DEFAULT_CACHE_SIZE,
        private var _apiKey: String? = null
    ) {
        fun build(): ClientConfig {
            return ClientConfig(
                baseUrl = baseUrl,
                readTimeout = _readTimeout,
                writeTimeout = _writeTimeout,
                connectTimeout = _connectTimeout,
                cacheEnabled = _cacheEnabled,
                cacheMaxAge = _cacheMaxAge,
                cacheSize = _cacheSize,
                apiKey = _apiKey
            )
        }

        fun readTimeout(readTimeout: Timeout) = apply { if (readTimeout.timeout >= 0) _readTimeout = readTimeout }

        fun writeTimeout(writeTimeout: Timeout) = apply { if (writeTimeout.timeout >= 0) _writeTimeout = writeTimeout }

        fun connectTimeout(connectTimeout: Timeout) = apply { if (connectTimeout.timeout >= 0) _connectTimeout = connectTimeout }

        fun cacheEnabled(cacheEnabled: Boolean) = apply { _cacheEnabled = cacheEnabled }

        fun cacheMaxAge(cacheMaxAge: Timeout) = apply { if (cacheMaxAge.timeout >= 0) _cacheMaxAge = cacheMaxAge }

        fun cacheSize(cacheSize: Long) = apply { if (cacheSize >= 0) _cacheSize = cacheSize }

        fun apiKey(apiKey: String) = apply { _apiKey = apiKey }
    }
}

data class Timeout(
    val timeout: Long,
    val unit: TimeUnit
)