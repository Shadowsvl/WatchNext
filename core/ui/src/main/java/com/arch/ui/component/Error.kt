package com.arch.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.arch.design_system.annotations.ThemePreviews
import com.arch.design_system.icon.AppIcons
import com.arch.design_system.theme.AppTheme
import com.arch.design_system.theme.basePadding
import com.arch.design_system.theme.smallPadding
import com.arch.ui.R

@Composable
fun ApiKeyError(
    message: String,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Column(
            verticalArrangement = Arrangement.spacedBy(smallPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(basePadding)
        ) {
            Icon(imageVector = AppIcons.Warning, contentDescription = "Warning")
            Text(
                text = stringResource(id = R.string.notify_invalid_api_key),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
            Text(
                text = message,
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}

@ThemePreviews
@Composable
fun ApiKeyErrorPreview() {
    AppTheme {
        ApiKeyError(message = "Llave inválida")
    }
}