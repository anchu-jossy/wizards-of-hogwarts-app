package com.example.wizardsofhogwarts

import org.junit.Assert.assertEquals
import org.junit.Test

import java.text.SimpleDateFormat
import java.util.*

class DateExtensionsTest {

    private fun String?.toFormattedDate(): String {
        if (this.isNullOrEmpty()) return "Unknown Date"

        val inputFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

        return try {
            val date: Date = inputFormat.parse(this) ?: return "Invalid Date"
            outputFormat.format(date)
        } catch (e: Exception) {
            "Error Formatting Date"
        }
    }

    @Test
    fun `test valid date`() {
        assertEquals("15 Aug 2023", "15-08-2023".toFormattedDate())
    }

    @Test
    fun `test empty string`() {
        assertEquals("Unknown Date", "".toFormattedDate())
    }

    @Test
    fun `test null input`() {
        assertEquals("Unknown Date", null.toFormattedDate())
    }

    @Test
    fun `test invalid date format`() {
        assertEquals("Invalid Date", "2023-08-15".toFormattedDate())
    }
}
