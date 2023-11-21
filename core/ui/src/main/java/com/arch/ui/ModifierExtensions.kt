package com.arch.ui

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color

fun Modifier.loadingEffect(): Modifier = composed {
    val infiniteTransition = rememberInfiniteTransition(label = "transition")

    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.1f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 1000
                0.6f at 500
                0.8f at 800
            },
            repeatMode = RepeatMode.Reverse
        ),
        label = "animation"
    )

    background(
        color = Color.LightGray
            .copy(alpha = alpha)
    )
}