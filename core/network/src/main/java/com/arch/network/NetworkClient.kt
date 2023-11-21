package com.arch.network

interface NetworkClient {
    fun <T> getApiService(apiInterface: Class<T>): T
}