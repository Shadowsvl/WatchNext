package com.arch.database.schema.mapper

import com.arch.database.schema.model.WatchMediaEntity
import com.arch.model.data.WatchMedia

fun WatchMediaEntity.asWatchMedia() = WatchMedia(
    id = id,
    originalTitle= originalTitle,
    title = title,
    overview = overview,
    releaseDate = releaseDate,
    score = score,
    scoreValue = scoreValue,
    posterUrl = posterUrl,
    backdropUrl = backdropUrl,
    mediaType = mediaType
)

fun WatchMedia.asWatchMediaEntity() = WatchMediaEntity(
    id = id,
    originalTitle= originalTitle,
    title = title,
    overview = overview,
    releaseDate = releaseDate,
    score = score,
    scoreValue = scoreValue,
    posterUrl = posterUrl,
    backdropUrl = backdropUrl,
    mediaType = mediaType
)