package com.arch.ui

import androidx.compose.foundation.lazy.grid.LazyGridState

fun LazyGridState.isCloseToBottom() =
    ((layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: layoutInfo.totalItemsCount) >= layoutInfo.totalItemsCount - 12)