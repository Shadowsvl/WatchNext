package com.arch.design_system.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.arch.design_system.theme.LocalTintTheme

@Composable
fun AppAsyncImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.None,
    alignment: Alignment = Alignment.Center,
    contentDescription: String? = null,
    placeholder: Painter? = null,
    error: Painter? = null
) {
    val iconTint = LocalTintTheme.current.iconTint

    AsyncImage(
        model = imageUrl,
        placeholder = placeholder,
        error = error,
        contentScale = contentScale,
        alignment = alignment,
        contentDescription = contentDescription,
        colorFilter = if (iconTint != null) ColorFilter.tint(iconTint) else null,
        modifier = modifier
    )
}