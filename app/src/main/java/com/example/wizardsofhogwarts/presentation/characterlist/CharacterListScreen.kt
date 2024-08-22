package com.example.wizardsofhogwarts.presentation.characterlist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.wizardsofhogwarts.R
import com.example.wizardsofhogwarts.domain.model.Character
import com.example.wizardsofhogwarts.presentation.component.CircularIndeterminateProgressBar
import com.example.wizardsofhogwarts.presentation.component.NoDataPlaceHolder
import com.example.wizardsofhogwarts.presentation.component.ErrorView

/**
 * Composable function to display the character list screen.
 *
 * @param onItemClick Callback for when an item is clicked.
 * @param state State of the character list screen.
 * @param characterList List of characters to display.
 * @param searchText Current text in the search bar.
 * @param onSearchTextChange Callback to handle search text changes.
 */
@Composable
fun CharacterListScreen(
    onItemClick: (Character) -> Unit,
    state: CharacterListState,
    characterList: List<Character>,
    searchText: String,
    onSearchTextChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        SearchBar(
            searchText = searchText,
            onSearchTextChange = onSearchTextChange
        )

        when {
            state.isLoading -> CircularIndeterminateProgressBar(isDisplayed = true)
            state.error.isNotBlank() -> ErrorView(
                text = state.error,
                color = MaterialTheme.colorScheme.error
            )

            characterList.isEmpty() -> NoDataPlaceHolder()
            else -> LazyColumn(
                contentPadding = PaddingValues(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(characterList) { character ->
                    CharacterListItem(character = character, onItemClick = onItemClick)
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
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        singleLine = true,
        value = searchText,
        onValueChange = onSearchTextChange,
        modifier = Modifier
            .fillMaxWidth()
            .testTag(stringResource(R.string.search_bar_tag)),
        placeholder = {
            Text(
                text = stringResource(id = R.string.search),
                color = Color.Gray
            )
        },
        trailingIcon = {
            if (searchText.isNotEmpty()) {
                IconButton(onClick = {
                    onSearchTextChange("")
                    keyboardController?.hide()
                }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        tint = MaterialTheme.colorScheme.onSurface,
                        contentDescription = stringResource(R.string.clear_search)
                    )
                }
            }
        }
    )
}
