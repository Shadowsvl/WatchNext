package com.arch.ui.component

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arch.design_system.annotations.ThemePreviews
import com.arch.design_system.icon.AppIcons
import com.arch.design_system.theme.AppTheme

@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    SmallFloatingActionButton(
        onClick = onClick,
        shape = CircleShape,
        modifier = modifier
    ) {
        Icon(
            imageVector = AppIcons.ArrowBack,
            contentDescription = "Back"
        )
    }
}

@ThemePreviews
@Composable
fun BackButtonPreview() {
    AppTheme {
        BackButton {}
    }
}