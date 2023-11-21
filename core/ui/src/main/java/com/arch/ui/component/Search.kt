package com.arch.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arch.design_system.annotations.ThemePreviews
import com.arch.design_system.component.AppSearchTextField
import com.arch.design_system.theme.AppTheme
import com.arch.design_system.theme.basePadding
import com.arch.design_system.theme.smallPadding

@Composable
fun SearchToolbar(
    searchQuery: String,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onSearchQueryChanged: (String) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(25.dp)
                .background(color = MaterialTheme.colorScheme.surface)
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(basePadding),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
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
            AppSearchTextField(
                onSearchQueryChanged = onSearchQueryChanged,
                searchQuery = searchQuery
            )
        }
    }
}

@ThemePreviews
@Composable
fun SearchToolbarPreview() {
    AppTheme {
        SearchToolbar(
            searchQuery = "",
            onBackClick = {},
            onSearchQueryChanged = {}
        )
    }
}