package com.example.wizardsofhogwarts.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.example.wizardsofhogwarts.R
import com.example.wizardsofhogwarts.ui.character_detail.CharacterDetailScreen
import com.example.wizardsofhogwarts.ui.characterlist.CharacterListScreen
import com.example.wizardsofhogwarts.ui.characterlist.CharacterListViewModel


@Composable
fun Navigation(navController: NavHostController, viewModel: CharacterListViewModel) {
    NavHost(navController = navController, startDestination = Screen.CharactersListScreen.route) {
        composable(Screen.CharactersListScreen.route) {
            CharacterListScreen(navController = navController, viewModel = viewModel)
        }

        composable(
            route = Screen.CharacterDetailScreen.route
        ) {
            CharacterDetailScreen(viewModel = viewModel)
        }
    }
}


@Composable
fun navigationTitle(navController: NavController): String {
    return when (currentRoute(navController)) {
        Screen.CharactersListScreen.route -> stringResource(id = R.string.characters)
        Screen.CharacterDetailScreen.route -> stringResource(id = R.string.character_details)
        else -> {
            ""
        }
    }
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route?.substringBeforeLast("/")
}

