package com.example.wizardsofhogwarts.presentation.characterlist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wizardsofhogwarts.R
import com.example.wizardsofhogwarts.presentation.component.CircularIndeterminateProgressBar
import com.example.wizardsofhogwarts.presentation.component.NoDataPlaceHolder
import com.example.wizardsofhogwarts.presentation.component.ErrorView
import com.example.wizardsofhogwarts.presentation.navigation.Screen
import com.example.wizardsofhogwarts.presentation.shared.CharacterListViewModel

/**
 * Composable function to display the character list screen.
 *
 * @param navController Navigation controller to handle navigation actions.
 * @param viewModel ViewModel providing character data and search functionality.
 */
@Composable
fun CharacterListScreen(
    navController: NavController,
    viewModel: CharacterListViewModel
) {
    // Collect state, character list, and search text from the ViewModel
    val state by viewModel.state.collectAsState()
    val characterList by viewModel.characterList.collectAsState()
    val searchText by viewModel.searchText.collectAsState()

    // Main container for the screen
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp) // Padding around the content
    ) {
        // Search bar for filtering the character list
        SearchBar(
            searchText = searchText,
            onSearchTextChange = viewModel::onSearchTextChange
        )

        // Show a loading spinner while data is loading
        if (state.isLoading) {
            CircularIndeterminateProgressBar(isDisplayed = true)
        }
        // Show an error message if an error occurs
        else if (state.error.isNotBlank()) {
            ErrorView(text = state.error, color = MaterialTheme.colorScheme.error)
        }
        // Display character list or a placeholder if no data is available
        else {
            if (characterList.isEmpty()) {
                NoDataPlaceHolder()
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(characterList) { character ->
                        CharacterListItem(character = character, onItemClick = { clickedItem ->
                            viewModel.addCharacter(clickedItem)
                            navController.navigate(Screen.CharacterDetailScreen.route)
                        })
                    }
                }
            }
        }
    }
}

/**
 * Composable function to display a search bar.
 *
 * @param searchText The current text in the search bar.
 * @param onSearchTextChange Callback to handle text changes.
 */
@Composable
fun SearchBar(
    searchText: String,
    onSearchTextChange: (String) -> Unit
) {
    TextField(
        singleLine = true,
        value = searchText,
        onValueChange = onSearchTextChange,
        modifier = Modifier.fillMaxWidth(), // Full width of its parent
        placeholder = {
            Text(
                text = stringResource(id = R.string.search),
                color = Color.Gray
            )
        },
        trailingIcon = {
            if (searchText.isNotEmpty()) {
                IconButton(onClick = { onSearchTextChange("") }) {

                    Icon(
                        imageVector = Icons.Default.Close,
                        tint = MaterialTheme.colorScheme.onSurface,
                        contentDescription = "Clear search"
                    )
                }
            }
        }
    )
}
