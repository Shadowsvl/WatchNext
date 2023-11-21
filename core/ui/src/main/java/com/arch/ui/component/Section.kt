package com.arch.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.arch.design_system.annotations.ThemePreviews
import com.arch.design_system.theme.AppTheme
import com.arch.design_system.theme.basePadding
import com.arch.ui.MediaSectionBaselinePadding
import com.arch.ui.loadingEffect
import com.arch.ui.watchMediaList

@Composable
fun MediaSection(
    title: String,
    modifier: Modifier = Modifier,
    topBaselinePadding: Dp = MediaSectionBaselinePadding,
    content: @Composable () -> Unit
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .paddingFromBaseline(top = topBaselinePadding, bottom = basePadding)
                .padding(horizontal = basePadding)
        )
        content()
    }
}

@Composable
fun MediaSectionLoading(
    modifier: Modifier = Modifier,
    contentLoading: @Composable () -> Unit
) {
    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .padding(top = 28.dp, bottom = basePadding)
                .padding(horizontal = basePadding)
                .size(150.dp, 20.dp)
                .clip(MaterialTheme.shapes.medium)
                .loadingEffect()
        )
        contentLoading()
    }
}

@ThemePreviews
@Composable
fun SectionPreview() {
    AppTheme {
        Surface {
            MediaSection(
                title = "Películas recientes",
                modifier = Modifier.fillMaxWidth()
            ) {
                PosterCarousel(
                    items = watchMediaList,
                    onPosterClick = {}
                )
            }
        }
    }
}