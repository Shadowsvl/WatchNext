package com.arch.network.util

import java.util.Locale

object LocaleConfig {

    private var defaultLanguage = Locale.getDefault().language

    val language: String = when(defaultLanguage) {
        "es" -> "es"
        else -> "en-US"
    }

    val region: String = when(defaultLanguage) {
        "es" -> "MX"
        else -> "US"
    }
}