package com.arch.model.data

import androidx.annotation.StringRes

data class MainSection(
    @StringRes val titleId: Int,
    val watchMediaList: List<WatchMedia>,
    val sectionType: SectionType = SectionType.Poster
)

enum class SectionType {
    Poster,
    Banner
}
