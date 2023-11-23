package com.arch.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arch.design_system.annotations.ThemePreviews
import com.arch.design_system.component.AppAsyncImage
import com.arch.design_system.icon.AppIcons
import com.arch.design_system.theme.AppTheme
import com.arch.design_system.theme.basePadding
import com.arch.design_system.theme.smallPadding
import com.arch.model.data.MediaType
import com.arch.model.data.WatchMedia
import com.arch.ui.PosterHeight
import com.arch.ui.PosterWidth
import com.arch.ui.R
import com.arch.ui.movieA
import com.arch.ui.tvB

@Composable
fun DetailBanner(
    backdropUrl: String,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    Box(
        modifier = modifier
    ) {
        AppAsyncImage(
            imageUrl = backdropUrl,
            contentScale = ContentScale.FillHeight,
            alignment = Alignment.TopCenter,
            placeholder = painterResource(id = R.drawable.ic_backdrop_placeholder),
            error = painterResource(id = R.drawable.ic_backdrop_placeholder),
            contentDescription = "Banner",
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
                .background(MaterialTheme.colorScheme.surfaceVariant)
        )
        BackButton(
            modifier = Modifier.padding(start = smallPadding, top = 44.dp),
            onClick = onBackClick
        )
    }
}

@Composable
fun MediaDetail(
    watchMedia: WatchMedia,
    isSaved: Boolean,
    modifier: Modifier = Modifier,
    onActionClick: (WatchMedia) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(basePadding),
        modifier = modifier
    ) {
        Poster(
            imageUrl = watchMedia.posterUrl,
            width = PosterWidth,
            height = PosterHeight
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = PosterHeight)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = watchMedia.title,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                if (watchMedia.title != watchMedia.originalTitle) {
                    Text(
                        text = watchMedia.originalTitle,
                        style = MaterialTheme.typography.labelLarge,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(smallPadding),
                    modifier = Modifier.padding(top = smallPadding)
                ) {
                    InfoChip(label = watchMedia.releaseDate)
                    InfoChip(
                        label = when(watchMedia.mediaType) {
                            MediaType.Movie -> stringResource(id = R.string.media_type_movie)
                            MediaType.Tv -> stringResource(id = R.string.media_type_tv)
                        }
                    )
                }
            }
            ScoreIndicator(
                watchMedia = watchMedia,
                modifier = Modifier.align(Alignment.BottomStart)
            )
            IconToggleButton(
                checked = isSaved,
                onCheckedChange = { onActionClick(watchMedia) },
                colors = IconButtonDefaults.iconToggleButtonColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                ),
                modifier = Modifier
                    .size(55.dp)
                    .align(Alignment.BottomEnd)
            ) {
                if (isSaved) {
                    Icon(imageVector = AppIcons.Check, contentDescription = "Check")
                } else {
                    Icon(imageVector = AppIcons.Add, contentDescription = "Add")
                }
            }
        }
    }
}

@Composable
fun Overview(
    overview: String,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = smallPadding)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(smallPadding))
                Text(
                    text = overview,
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(smallPadding))
            }
            FadeBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(basePadding)
                    .align(Alignment.TopCenter)
            )
            FadeBox(
                fadeEffect = FadeEffect.Bottom,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(basePadding)
                    .align(Alignment.BottomCenter)
            )
        }
    }
}

@Composable
fun ScoreIndicator(
    watchMedia: WatchMedia,
    modifier: Modifier = Modifier
) {
    Box(contentAlignment = Alignment.Center, modifier = modifier.size(45.dp)) {
        CircularProgressIndicator(
            progress = 1f,
            color = Color.LightGray,
            modifier = Modifier.fillMaxSize()
        )
        CircularProgressIndicator(
            progress = watchMedia.scoreValue,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.fillMaxSize()
        )
        Text(
            text = watchMedia.score,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun DetailBannerPreview() {
    AppTheme {
        DetailBanner(
            backdropUrl = movieA.backdropUrl,
            onBackClick = {}
        )
    }
}

@ThemePreviews
@Composable
fun WatchMediaDetailPreview() {
    AppTheme {
        Surface {
            MediaDetail(
                watchMedia = movieA,
                isSaved = false,
                onActionClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(basePadding)
            )
        }
    }
}

@ThemePreviews
@Composable
fun OverviewPreview() {
    AppTheme {
        Overview(
            overview = movieA.overview,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        )
    }
}

@ThemePreviews
@Composable
fun ScoreIndicatorPreview() {
    AppTheme {
        Surface {
            ScoreIndicator(watchMedia = tvB, modifier = Modifier.padding(basePadding))
        }
    }
}