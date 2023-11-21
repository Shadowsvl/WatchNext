package com.arch.common.formatter

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class DateFormatterTest {

    @Test
    fun `On valid string date returns desired format`() {
        val date = "2023-11-01"

        try {
            val result = DateFormatter.changeDatePattern(date, "yyyy-MM-dd", "dd/MM/yyyy")

            assertEquals("01/11/2023", result)
        } catch (e: Exception) {
            e.localizedMessage
        }
    }

    @Test
    fun `On blank argument throws IllegalArgumentException`() {
        assertThrows(
            IllegalArgumentException::class.java
        ) {
            DateFormatter.changeDatePattern("", "yyyy-MM-dd", "dd/MM/yyyy")
        }
    }

    @Test
    fun `On invalid argument throws IllegalArgumentException`() {
        assertThrows(
            IllegalArgumentException::class.java
        ) {
            DateFormatter.changeDatePattern("Not a date", "yyyy-MM-dd", "dd/MM/yyyy")
        }
    }
}