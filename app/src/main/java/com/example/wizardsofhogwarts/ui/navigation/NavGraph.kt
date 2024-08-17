package com.example.wizardsofhogwarts.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.wizardsofhogwarts.R
import com.example.wizardsofhogwarts.ui.character_detail.CharacterDetailScreen
import com.example.wizardsofhogwarts.ui.characterlist.CharacterListScreen


@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.CharactersListScreen.route) {
        composable(Screen.CharactersListScreen.route) {
            CharacterListScreen(navController = navController,)
        }
        composable(Screen.CharacterDetailScreen.route) {
             CharacterDetailScreen(navController = navController)
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

