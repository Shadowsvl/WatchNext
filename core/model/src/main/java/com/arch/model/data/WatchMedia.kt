package com.arch.model.data

data class WatchMedia(
    val id: Long,
    val originalTitle: String,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val score: String,
    val scoreValue: Float,
    val posterUrl: String,
    val backdropUrl: String,
    val mediaType: MediaType
)

enum class MediaType {
    Movie,
    Tv
}
