package com.example.wizardsofhogwarts.presentation.character_detail

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.wizardsofhogwarts.domain.model.Character
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharacterDetailScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val testCharacter = Character(
        id = "9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8",
        name = "Harry Potter",
        house = "Gryffindor",
        image = "https://ik.imagekit.io/hpapi/harry.jpg"
    )

    @Before
    fun setUp() {
        composeTestRule.setContent {
            CharacterDetailScreen(character = testCharacter)
        }
    }

    @Test
    fun testCharacterImageDisplayed() {
        composeTestRule.onNodeWithContentDescription(
            "Image of a character from the Hogwarts universe"
        ).assertExists()
    }

    @Test
    fun testCharacterDetailsDisplayed() {
        // Verify that the name of the character is displayed
        composeTestRule.onNodeWithText(testCharacter.name).assertExists()
    }


}
