package com.arch.network

import com.google.gson.Gson
import retrofit2.Response

sealed interface ApiResponse<out T> {
    data class Success<T>(val data: T?) : ApiResponse<T>
    data class HttpError(val errorData: Any?, val statusMessage: String, val httpCode: HttpCode) : ApiResponse<Nothing>
    data class Failure(val exception: Throwable) : ApiResponse<Nothing>
}

fun <T> Response<T>.asApiResponse(
    dtoClass: Class<*>? = null,
    gson: Gson? = null
): ApiResponse<T> {
    try {
        if (isSuccessful) return ApiResponse.Success(data = body())

        return if (gson == null) {
            ApiResponse.HttpError(
                errorData = null,
                statusMessage = message(),
                httpCode = HttpCode.withCode(code())
            )
        } else {
            ApiResponse.HttpError(
                errorData = dtoClass?.let { gson.fromJson(errorBody()?.string(), it) },
                statusMessage = message(),
                httpCode = HttpCode.withCode(code())
            )
        }
    } catch (e: Throwable) {
        return ApiResponse.Failure(e)
    }
}

enum class HttpCode(val value: Int) {
    BadRequest(400),
    Unauthorized(401),
    Forbidden(403),
    NotFound(404),
    MethodNotAllowed(405),
    NotAcceptable(406),
    RequestTimeout(408),
    Conflict(409),
    InternalServerError(500),
    NotImplemented(501),
    BadGateway(502),
    ServiceUnavailable(503),
    GatewayTimeout(504),
    Unknown(-1);

    companion object {
        private val map = values().associateBy(HttpCode::value)
        fun withCode(errorCode: Int) = map[errorCode] ?: Unknown
    }
}