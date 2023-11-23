package com.arch.ui.component

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
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

@Composable
fun MediaToggleButton(
    isSaved: Boolean,
    modifier: Modifier = Modifier,
    onCheckedChange: (Boolean) -> Unit
) {
    FilledIconToggleButton(
        checked = isSaved,
        onCheckedChange = onCheckedChange,
        colors = IconButtonDefaults.filledIconToggleButtonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            checkedContainerColor = MaterialTheme.colorScheme.primary,
            checkedContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        modifier = modifier
    ) {
        if (isSaved) {
            Icon(imageVector = AppIcons.Check, contentDescription = "Check")
        } else {
            Icon(imageVector = AppIcons.Add, contentDescription = "Add")
        }
    }
}

@ThemePreviews
@Composable
fun BackButtonPreview() {
    AppTheme {
        BackButton {}
    }
}

@ThemePreviews
@Composable
fun MediaToggleButtonPreview() {
    AppTheme {
        MediaToggleButton(
            isSaved = false,
            onCheckedChange = {}
        )
    }
}