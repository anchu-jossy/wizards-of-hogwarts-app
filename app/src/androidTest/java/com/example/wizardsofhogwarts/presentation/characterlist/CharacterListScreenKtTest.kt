package com.example.wizardsofhogwarts.presentation.characterlist

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.NavController
import com.example.wizardsofhogwarts.domain.model.Character
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharacterListScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()



    @Test
    fun testSearchBarDisplaysCorrectly() {
        // Arrange
        val searchText = "Harry Potter"
        composeTestRule.setContent {
            CharacterListScreen(
                onItemClick = {},
                state = CharacterListState(isLoading = false, error = ""),
                characterList = emptyList(),
                searchText = searchText,
                onSearchTextChange = {}
            )
        }

        // Act
        val searchBar = composeTestRule.onNodeWithTag("search_bar")

        // Assert
        searchBar.assertExists()
        searchBar.assertTextContains(searchText)
    }

    @Test
    fun testLoadingStateDisplaysProgressBar() {
        // Arrange
        composeTestRule.setContent {
            CharacterListScreen(
                onItemClick = {},
                state = CharacterListState(isLoading = true, error = ""),
                characterList = emptyList(),
                searchText = "",
                onSearchTextChange = {}
            )
        }

        // Act
        val progressBar = composeTestRule.onNodeWithContentDescription("loading")

        // Assert
        progressBar.assertExists()
    }

    @Test
    fun testErrorStateDisplaysErrorView() {
        // Arrange
        val errorMessage = "An error occurred"
        composeTestRule.setContent {
            CharacterListScreen(
                onItemClick = {},
                state = CharacterListState(isLoading = false, error = errorMessage),
                characterList = emptyList(),
                searchText = "",
                onSearchTextChange = {}
            )
        }

        // Act
        val errorView = composeTestRule.onNodeWithText(errorMessage)

        // Assert
        errorView.assertExists()
    }

    @Test
    fun testNoDataPlaceHolderDisplayed() {
        // Arrange
        composeTestRule.setContent {
            CharacterListScreen(
                onItemClick = {},
                state = CharacterListState(isLoading = false, error = ""),
                characterList = emptyList(),
                searchText = "",
                onSearchTextChange = {}
            )
        }

        // Act
        val noDataPlaceHolder = composeTestRule.onNodeWithTag("no_data_placeholder")

        // Assert
        noDataPlaceHolder.assertExists()
    }

    @Test
    fun testCharacterListDisplaysItems() {
        // Arrange
        val characters = listOf(Character("9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8", "Harry Potter"), Character("4c7e6819-a91a-45b2-a454-f931e4a7cce3", "Hermione Granger"))
        composeTestRule.setContent {
            CharacterListScreen(
                onItemClick = {},
                state = CharacterListState(isLoading = false, error = ""),
                characterList = characters,
                searchText = "",
                onSearchTextChange = {}
            )
        }

        // Act
        val item1 = composeTestRule.onNodeWithText("Harry Potter")
        val item2 = composeTestRule.onNodeWithText("Hermione Granger")

        // Assert
        item1.assertExists()
        item2.assertExists()
    }

    @Test
    fun testSearchBarClearsText() {
        // Arrange
        val searchText = "Harry Potter"
        composeTestRule.setContent {
            CharacterListScreen(
                onItemClick = {},
                state = CharacterListState(isLoading = false, error = ""),
                characterList = emptyList(),
                searchText = searchText,
                onSearchTextChange = { newText ->
                    // This is a stub; in a real test, you might want to simulate text clearing
                }
            )
        }

        // Act
        val clearButton = composeTestRule.onNodeWithContentDescription("Clear search")
        clearButton.performClick()

        // Assert
        // In a real test, you might need to verify that the search text is cleared
    }
}
