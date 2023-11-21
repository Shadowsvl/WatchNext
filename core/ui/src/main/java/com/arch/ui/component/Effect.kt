package com.arch.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun FadeBox(
    modifier: Modifier = Modifier,
    fadeColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    fadeEffect: FadeEffect = FadeEffect.Top
) {
    Box(
        modifier = modifier
            .then(
                Modifier
                    .background(
                        when(fadeEffect) {
                            FadeEffect.Top -> Brush.verticalGradient(listOf(fadeColor, Color.Transparent))
                            FadeEffect.Bottom -> Brush.verticalGradient(listOf(Color.Transparent, fadeColor))
                            FadeEffect.Start -> Brush.horizontalGradient(listOf(fadeColor, Color.Transparent))
                            FadeEffect.End -> Brush.horizontalGradient(listOf(Color.Transparent, fadeColor))
                        }
                    )
            )
    )
}

enum class FadeEffect {
    Top,
    Bottom,
    Start,
    End
}