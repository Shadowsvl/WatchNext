package com.arch.database.schema.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.arch.model.data.MediaType

@Entity(tableName = "watch_media")
data class WatchMediaEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "original_title") val originalTitle: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "release_date") val releaseDate: String,
    @ColumnInfo(name = "score") val score: String,
    @ColumnInfo(name = "score_value") val scoreValue: Float,
    @ColumnInfo(name = "poster_url") val posterUrl: String,
    @ColumnInfo(name = "backdrop_url") val backdropUrl: String,
    @ColumnInfo(name = "media_type") val mediaType: MediaType,
    @ColumnInfo(name = "inserted_timestamp") val insertedTimestamp: Long = System.currentTimeMillis()
)
