package com.example.wizardsofhogwarts.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.wizardsofhogwarts.presentation.character_detail.CharacterDetailScreen
import com.example.wizardsofhogwarts.presentation.characterlist.CharacterListScreen
import com.example.wizardsofhogwarts.presentation.shared.CharacterListViewModel

/**
 * Sets up the navigation graph for the application.
 *
 * @param navController The [NavHostController] used for managing navigation.
 * @param viewModel The [CharacterListViewModel] shared between screens for managing state.
 */
@Composable
fun Navigation(
    navController: NavHostController,
    viewModel: CharacterListViewModel
) {
    // Define the navigation graph
    NavHost(
        navController = navController,
        startDestination = Screen.CharactersListScreen.route // The initial screen to display
    ) {
        // Define the composable for the character list screen
        composable(Screen.CharactersListScreen.route) {
            CharacterListScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        // Define the composable for the character detail screen
        composable(Screen.CharacterDetailScreen.route) {
            CharacterDetailScreen(viewModel = viewModel)
        }
    }
}
