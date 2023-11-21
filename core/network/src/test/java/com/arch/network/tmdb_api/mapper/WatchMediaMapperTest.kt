package com.arch.network.tmdb_api.mapper

import com.arch.model.data.WatchMedia
import com.arch.network.tmdb_api.model.MovieDto
import com.arch.network.tmdb_api.model.TvDto
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertEquals
import org.junit.Test

class WatchMediaMapperTest {

    @Test
    fun `MovieDto as WatchMedia returns correct mapping`() {
        val movieDto = MovieDto(
            backdropPath = "/321.jpg",
            overview = "Overview",
            title = "Title",
            id = 1,
            originalTitle = "Original Title",
            posterPath = "/321.jpg",
            releaseDate = "2021-12-15",
            voteAverage = 7.1f
        )
        val media = movieDto.asWatchMedia()

        assertThat(media, instanceOf(WatchMedia::class.java))
        assertEquals("https://image.tmdb.org/t/p/original/321.jpg", media.backdropUrl)
        assertEquals("Overview", media.overview)
        assertEquals("Title", media.title)
        assertEquals(1, media.id)
        assertEquals("Original Title", media.originalTitle)
        assertEquals("https://image.tmdb.org/t/p/w342/321.jpg", media.posterUrl)
        assertEquals("15/12/2021", media.releaseDate)
        assertEquals("7.1", media.score)
    }

    @Test
    fun `TvDto as WatchMedia returns correct mapping`() {
        val tvDto = TvDto(
            backdropPath = "/321.jpg",
            overview = "Overview",
            name = "Title",
            id = 1,
            originalName = "Original Title",
            posterPath = "/321.jpg",
            firstAirDate = "2021-12-15",
            voteAverage = 7.1f
        )
        val media = tvDto.asWatchMedia()

        assertThat(media, instanceOf(WatchMedia::class.java))
        assertEquals("https://image.tmdb.org/t/p/original/321.jpg", media.backdropUrl)
        assertEquals("Overview", media.overview)
        assertEquals("Title", media.title)
        assertEquals(1, media.id)
        assertEquals("Original Title", media.originalTitle)
        assertEquals("https://image.tmdb.org/t/p/w342/321.jpg", media.posterUrl)
        assertEquals("15/12/2021", media.releaseDate)
        assertEquals("7.1", media.score)
    }
}