package com.arch.network.tmdb_api.model

import com.google.gson.annotations.SerializedName

data class WatchMediaErrorDto(
    @SerializedName("success")
    val success: Boolean?,
    @SerializedName("status_code")
    val statusCode: Int?,
    @SerializedName("status_message")
    val statusMessage: String?
)