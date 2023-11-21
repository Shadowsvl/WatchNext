package com.arch.network.tmdb_api.mapper

import com.arch.common.formatter.DateFormatter
import com.arch.model.data.MediaType
import com.arch.model.data.WatchMedia
import com.arch.network.BuildConfig
import com.arch.network.tmdb_api.model.MovieDto
import com.arch.network.tmdb_api.model.TvDto

fun MovieDto.asWatchMedia() = WatchMedia(
    id = id ?: 0L,
    originalTitle = originalTitle ?: "",
    title = title ?: "",
    overview = overview ?: "",
    releaseDate = releaseDate?.toMediaDate() ?: "",
    score = voteAverage?.toScoreString() ?: "",
    scoreValue = voteAverage?.let { it / 10 } ?: 0f,
    posterUrl = posterPath?.toMediaImageUrl(MediaSize.Poster) ?: "",
    backdropUrl = backdropPath?.toMediaImageUrl(MediaSize.Backdrop) ?: "",
    mediaType = MediaType.Movie
)

fun TvDto.asWatchMedia() = WatchMedia(
    id = id ?: 0L,
    originalTitle = originalName ?: "",
    title = name ?: "",
    overview = overview ?: "",
    releaseDate = firstAirDate?.toMediaDate() ?: "",
    score = voteAverage?.toScoreString() ?: "",
    scoreValue = voteAverage?.let { it / 10 } ?: 0f,
    posterUrl = posterPath?.toMediaImageUrl(MediaSize.Poster) ?: "",
    backdropUrl = backdropPath?.toMediaImageUrl(MediaSize.Backdrop) ?: "",
    mediaType = MediaType.Tv
)

private enum class MediaSize(val size: String) {
    Poster("w342"),
    Backdrop("original")
}

private fun String.toMediaImageUrl(mediaSize: MediaSize): String = BuildConfig.API_IMAGES_BASE_URL + mediaSize.size + this

private fun String.toMediaDate(): String {
    return try {
        DateFormatter.changeDatePattern(
            this,
            "yyyy-MM-dd",
            "dd/MM/yyyy"
        )
    } catch (e: Exception) {
        e.printStackTrace()
        this
    }
}

private fun Float.toScoreString(): String = if (this == 10f) "10" else String.format("%.1f", this)