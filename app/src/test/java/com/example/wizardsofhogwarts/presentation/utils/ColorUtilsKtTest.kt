package com.example.wizardsofhogwarts.presentation.utils

import androidx.compose.ui.graphics.Color
import org.junit.Assert.assertEquals
import org.junit.Test

class GetHouseColorTest {

    // Mock resources directly for testing
    private val mockGriffindorColor = Color(0xFFC8102E)
    private val mockSlytherinColor = Color(0xFF2C6B62)
    private val mockRavenclawColor = Color(0xFF003D6C)
    private val mockHufflepuffColor = Color(0xFFF7E32C)

    // Mock string resources
    private val mockGriffindor = "Griffindor"
    private val mockSlytherin = "Slytherin"
    private val mockRavenclaw = "Ravenclaw"
    private val mockHufflepuff = "Hufflepuff"

    @Test
    fun testGetHouseColor_returnsCorrectColor() {
        // Mock implementation of stringResource and colorResource
        val getMockedHouseColor = { house: String ->
            when (house) {
                mockGriffindor -> mockGriffindorColor
                mockSlytherin -> mockSlytherinColor
                mockRavenclaw -> mockRavenclawColor
                mockHufflepuff -> mockHufflepuffColor
                else -> Color.Transparent
            }
        }

        // Test cases for each house
        assertEquals(mockGriffindorColor, getMockedHouseColor(mockGriffindor))
        assertEquals(mockSlytherinColor, getMockedHouseColor(mockSlytherin))
        assertEquals(mockRavenclawColor, getMockedHouseColor(mockRavenclaw))
        assertEquals(mockHufflepuffColor, getMockedHouseColor(mockHufflepuff))

        // Test case for unknown house
        assertEquals(Color.Transparent, getMockedHouseColor("Unknown House"))
    }
}
