package com.arch.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.arch.design_system.annotations.ThemePreviews
import com.arch.design_system.icon.AppIcons
import com.arch.design_system.theme.AppTheme
import com.arch.design_system.theme.basePadding
import com.arch.design_system.theme.smallPadding
import com.arch.model.data.MediaType
import com.arch.model.data.WatchMedia
import com.arch.ui.MiniPosterHeight
import com.arch.ui.MiniPosterWidth
import com.arch.ui.R
import com.arch.ui.movieA

@Composable
fun DetailSheet(
    watchMedia: WatchMedia,
    isSaved: Boolean,
    onActionClick: (WatchMedia) -> Unit,
    onDetailClick: (WatchMedia) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(basePadding),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = basePadding)
            .padding(bottom = 24.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(basePadding),
            modifier = Modifier.fillMaxWidth()
        ) {
            Poster(
                imageUrl = watchMedia.posterUrl,
                width = MiniPosterWidth,
                height = MiniPosterHeight
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = MiniPosterHeight)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = watchMedia.title,
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(smallPadding)
                    ) {
                        InfoChip(label = watchMedia.releaseDate)
                        InfoChip(
                            label = when(watchMedia.mediaType) {
                                MediaType.Movie -> stringResource(id = R.string.media_type_movie)
                                MediaType.Tv -> stringResource(id = R.string.media_type_tv)
                            }
                        )
                    }
                    Spacer(modifier = Modifier.height(60.dp))
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
                    modifier = Modifier.align(Alignment.BottomEnd)
                ) {
                    if (isSaved) {
                        Icon(imageVector = AppIcons.Check, contentDescription = "Check")
                    } else {
                        Icon(imageVector = AppIcons.Add, contentDescription = "Add")
                    }
                }
            }
        }
        Text(
            text = watchMedia.overview,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 4,
            overflow = TextOverflow.Ellipsis
        )
        Divider()
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .clip(MaterialTheme.shapes.medium)
                .clickable { onDetailClick(watchMedia) }
        ) {
            Text(
                text = stringResource(id = R.string.watch_media_sheet_info),
                style = MaterialTheme.typography.labelLarge
            )
            Icon(imageVector = AppIcons.ArrowForward, contentDescription = "Arrow")
        }
    }
}

@ThemePreviews
@Composable
fun DetailSheetPreview() {
    AppTheme {
        Surface {
            DetailSheet(
                watchMedia = movieA,
                isSaved = false,
                onActionClick = {},
                onDetailClick = {}
            )
        }
    }
}