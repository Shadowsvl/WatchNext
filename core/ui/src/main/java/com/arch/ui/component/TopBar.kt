package com.arch.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.arch.design_system.annotations.ThemePreviews
import com.arch.design_system.icon.AppIcons
import com.arch.design_system.theme.AppTheme
import com.arch.design_system.theme.basePadding
import com.arch.design_system.theme.smallPadding
import com.arch.ui.R

@Composable
fun HomeTopBar(
    myListEnabled: Boolean,
    modifier: Modifier = Modifier,
    onMoviesClick: () -> Unit,
    onSeriesClick: () -> Unit,
    onMyListClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    Column(modifier = modifier) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.surface)
                .padding(top = 45.dp)
                .padding(horizontal = basePadding)
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_watch_next_icon),
                contentDescription = "WatchNextIcon",
                modifier = Modifier
                    .size(40.dp)
            )
            IconButton(onClick = onSearchClick) {
                Icon(
                    imageVector = AppIcons.Search,
                    contentDescription = null
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.surface,
                            Color.Transparent
                        ),
                        startY = 86f
                    )
                )
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(smallPadding),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                val textStyle = MaterialTheme.typography.headlineSmall
                val fontWeight = FontWeight.SemiBold

                Spacer(modifier = Modifier.width(smallPadding))

                TextButton(onClick = onMoviesClick) {
                    Text(
                        text = stringResource(id = R.string.screen_label_movies),
                        style = textStyle,
                        fontWeight = fontWeight
                    )
                }
                TextButton(onClick = onSeriesClick) {
                    Text(
                        text = stringResource(id = R.string.screen_label_series),
                        style = textStyle,
                        fontWeight = fontWeight
                    )
                }
                AnimatedVisibility(visible = myListEnabled) {
                    TextButton(onClick = onMyListClick) {
                        Text(
                            text = stringResource(id = R.string.screen_label_my_list),
                            style = textStyle,
                            fontWeight = fontWeight
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ScreenTopBar(
    screenLabel: String,
    onBackClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(35.dp)
                .background(color = MaterialTheme.colorScheme.surface)
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(basePadding),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(MaterialTheme.colorScheme.surface, Color.Transparent),
                        startY = 85f
                    )
                )
        ) {
            BackButton(
                modifier = Modifier.padding(start = smallPadding),
                onClick = onBackClick
            )
            Text(
                text = screenLabel,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.weight(weight = 1f))
            IconButton(
                onClick = onSearchClick,
                modifier = Modifier.padding(end = basePadding)
            ) {
                Icon(
                    imageVector = AppIcons.Search,
                    contentDescription = "Search"
                )
            }
        }
    }
}

@ThemePreviews
@Composable
fun HomeTopBarPreview() {
    AppTheme {
        Surface {
            HomeTopBar(
                myListEnabled = true,
                onMoviesClick = {},
                onSeriesClick = {},
                onMyListClick = {},
                onSearchClick = {}
            )
        }
    }
}

@ThemePreviews
@Composable
fun ScreenTopBarPreview() {
    AppTheme {
        Surface {
            ScreenTopBar(
                screenLabel = "Películas",
                onBackClick = {},
                onSearchClick = {}
            )
        }
    }
}