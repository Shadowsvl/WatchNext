package com.arch.common.result

sealed interface Result<out T> {
    data class Success<T>(val data: T) : Result<T>
    data class Error(val exception: Throwable? = null) : Result<Nothing>
}

fun <T> Result<T>.getDataOr(fallback: T): T {
    return (this as? Result.Success<T>)?.data ?: fallback
}

fun <T> Result<T>.getDataOrNull(): T? {
    return (this as? Result.Success<T>)?.data
}
