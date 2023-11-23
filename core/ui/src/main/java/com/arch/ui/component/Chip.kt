package com.arch.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.arch.design_system.theme.AppTheme
import com.arch.design_system.theme.extraSmallPadding

@Composable
fun InfoChip(
    label: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.labelSmall
) {
    Surface(
        color = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
    ) {
        Text(
            text = label,
            style = textStyle,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = extraSmallPadding)
        )
    }
}

@Preview
@Composable
fun InfoChipPreview() {
    AppTheme {
        Surface {
            InfoChip(
                label = "Película"
            )
        }
    }
}