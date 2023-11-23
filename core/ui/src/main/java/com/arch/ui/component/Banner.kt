package com.arch.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.arch.design_system.component.AppAsyncImage
import com.arch.design_system.theme.AppTheme
import com.arch.design_system.theme.basePadding
import com.arch.design_system.theme.smallPadding
import com.arch.model.data.WatchMedia
import com.arch.ui.BannerHeight
import com.arch.ui.BannerWidth
import com.arch.ui.R
import com.arch.ui.loadingEffect
import com.arch.ui.movieB

@Composable
fun Banner(
    media: WatchMedia,
    width: Dp = BannerWidth,
    height: Dp = BannerHeight,
    onBannerClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .requiredSize(width, height)
            .clip(MaterialTheme.shapes.medium)
            .clickable { onBannerClick() }
    ) {
        AppAsyncImage(
            imageUrl = media.backdropUrl,
            contentScale = ContentScale.FillBounds,
            placeholder = painterResource(R.drawable.ic_backdrop_placeholder),
            error = painterResource(R.drawable.ic_backdrop_placeholder),
            contentDescription = "Banner",
            modifier = Modifier.fillMaxSize()
        )
        Text(
            text = media.title,
            style = MaterialTheme.typography.bodyMedium.copy(
                shadow = Shadow(offset = Offset(2f, 2f))
            ),
            fontWeight = FontWeight.SemiBold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(smallPadding)
        )
    }
}

fun LazyListScope.banners(
    items: List<WatchMedia>,
    onBannerClick: (WatchMedia) -> Unit
) = items(items = items, key = { it.id }) { watchMedia ->
    Banner(media = watchMedia) {
        onBannerClick(watchMedia)
    }
}

@Composable
fun BannerCarousel(
    items: List<WatchMedia>,
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState(),
    onBannerClick: (WatchMedia) -> Unit
) {
    LazyRow(
        state = listState,
        contentPadding = PaddingValues(horizontal = basePadding),
        horizontalArrangement = Arrangement.spacedBy(basePadding),
        modifier = modifier
    ) {
        banners(
            items = items,
            onBannerClick = onBannerClick
        )
    }
}

@Composable
fun BannerCarouselLoading(
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(basePadding),
        modifier = Modifier
            .padding(start = basePadding)
            .then(modifier)
    ) {
        items(count = 2) {
            Box(
                modifier = Modifier
                    .size(BannerWidth, BannerHeight)
                    .clip(MaterialTheme.shapes.medium)
                    .loadingEffect()
            )
        }
    }
}

@Preview
@Composable
fun BannerPreview() {
    AppTheme {
        Surface {
            Banner(media = movieB)
        }
    }
}