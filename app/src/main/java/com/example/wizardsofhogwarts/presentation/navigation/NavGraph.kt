package com.example.wizardsofhogwarts.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.wizardsofhogwarts.domain.model.Character
import com.example.wizardsofhogwarts.presentation.character_detail.CharacterDetailScreen
import com.example.wizardsofhogwarts.presentation.characterlist.CharacterListScreen
import com.example.wizardsofhogwarts.presentation.characterlist.CharacterListState
import com.example.wizardsofhogwarts.presentation.characterlist.CharacterListViewModel

@Composable
fun Navigation(
    navController: NavHostController,
    viewModel: CharacterListViewModel,
    onItemClick: (Character) -> Unit,
    onSearchTextChange: (String) -> Unit,
    state: CharacterListState,
    characterList: List<Character>,
    searchText: String
) {
    NavHost(
        navController = navController,
        startDestination = Screen.CharactersListScreen.route // Starting route of the navigation graph
    ) {
        // Character List Screen
        composable(Screen.CharactersListScreen.route) {
            CharacterListScreen(
                onItemClick = onItemClick,
                state = state,
                characterList = characterList,
                searchText = searchText,
                onSearchTextChange = onSearchTextChange
            )
        }
        // Character Detail Screen
        composable(Screen.CharacterDetailScreen.route) {
            val character by viewModel.selectedCharacter.collectAsState()
            CharacterDetailScreen(character = character)
        }
    }
}
