package com.arch.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.arch.design_system.component.AppAsyncImage
import com.arch.design_system.theme.AppTheme
import com.arch.design_system.theme.basePadding
import com.arch.design_system.theme.smallPadding
import com.arch.model.data.WatchMedia
import com.arch.ui.PosterHeight
import com.arch.ui.PosterWidth
import com.arch.ui.R
import com.arch.ui.loadingEffect
import com.arch.ui.watchMediaList

@Composable
fun Poster(
    imageUrl: String,
    width: Dp = PosterWidth,
    height: Dp = PosterHeight,
    onPosterClick: () -> Unit = {}
) {
    AppAsyncImage(
        imageUrl = imageUrl,
        contentScale = ContentScale.FillBounds,
        placeholder = painterResource(R.drawable.ic_poster_placeholder),
        error = painterResource(R.drawable.ic_poster_placeholder),
        contentDescription = "Poster",
        modifier = Modifier
            .requiredSize(width, height)
            .clip(MaterialTheme.shapes.medium)
            .clickable { onPosterClick() }
    )
}

fun LazyListScope.posters(
    items: List<WatchMedia>,
    onPosterClick: (WatchMedia) -> Unit
) = items(items = items, key = { it.id }) { watchMedia ->
    Poster(imageUrl = watchMedia.posterUrl) {
        onPosterClick(watchMedia)
    }
}

@Composable
fun PosterCarousel(
    items: List<WatchMedia>,
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState(),
    onPosterClick: (WatchMedia) -> Unit
) {
    LazyRow(
        state = listState,
        contentPadding = PaddingValues(horizontal = basePadding),
        horizontalArrangement = Arrangement.spacedBy(basePadding),
        modifier = modifier
    ) {
        posters(
            items = items,
            onPosterClick = onPosterClick
        )
    }
}

@Composable
fun PosterCarouselLoading(
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(basePadding),
        modifier = Modifier
            .padding(start = basePadding)
            .then(modifier)
    ) {
        items(4) {
            Box(
                modifier = Modifier
                    .size(PosterWidth, PosterHeight)
                    .clip(MaterialTheme.shapes.medium)
                    .loadingEffect()
            )
        }
    }
}

fun LazyGridScope.posters(
    items: List<WatchMedia>,
    onPosterClick: (WatchMedia) -> Unit
) = items(items = items, key = { it.id }) { watchMedia ->
    Poster(imageUrl = watchMedia.posterUrl) {
        onPosterClick(watchMedia)
    }
}

@Composable
fun PosterGrid(
    items: List<WatchMedia>,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(basePadding),
    gridState: LazyGridState = rememberLazyGridState(),
    onPosterClick: (WatchMedia) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(PosterWidth),
        contentPadding = paddingValues,
        horizontalArrangement = Arrangement.spacedBy(basePadding),
        verticalArrangement = Arrangement.spacedBy(basePadding),
        state = gridState,
        modifier = modifier
    ) {
        posters(
            items = items,
            onPosterClick = onPosterClick
        )
    }
}

@Composable
fun PosterGridLoading(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(basePadding)
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(PosterWidth),
        contentPadding = paddingValues,
        horizontalArrangement = Arrangement.spacedBy(basePadding),
        verticalArrangement = Arrangement.spacedBy(basePadding),
        modifier = modifier
    ) {
        items(6) {
            Box(
                modifier = Modifier
                    .requiredSize(PosterWidth, PosterHeight)
                    .clip(MaterialTheme.shapes.medium)
                    .loadingEffect()
            )
        }
    }
}

@Preview
@Composable
fun PosterPreview() {
    AppTheme {
        Poster("")
    }
}

@Preview
@Composable
fun PosterCarouselPreview() {
    AppTheme {
        Surface {
            PosterCarousel(
                items = watchMediaList,
                onPosterClick = {}
            )
        }
    }
}

@Preview
@Composable
fun PosterGridPreview() {
    AppTheme {
        Surface {
            PosterGrid(
                items = watchMediaList,
                onPosterClick = {}
            )
        }
    }
}