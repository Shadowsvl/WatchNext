package com.arch.common.formatter

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

object DateFormatter {

    fun changeDatePattern(date: String, fromPattern: String, toPattern: String): String {
        if (fromPattern.isBlank() || toPattern.isBlank() || date.isBlank()) {
            throw IllegalArgumentException("Arguments can't be blank")
        }

        val formatterFrom = DateTimeFormatter.ofPattern(fromPattern, Locale.getDefault())
        val formatterTo = DateTimeFormatter.ofPattern(toPattern, Locale.getDefault())

        return try {
            val localDate = LocalDate.parse(date, formatterFrom)
            localDate.format(formatterTo)
        } catch (e: Exception) {
            throw IllegalArgumentException("Invalid argument for date or pattern")
        }
    }
}