package com.example.wizardsofhogwarts.presentation.navigation

/**
 * Sealed class representing the different navigation routes in the application.
 *
 * @property route The route string associated with the screen.
 */
sealed class Screen(val route: String) {

    /**
     * Represents the screen displaying the list of characters.
     */
    object CharactersListScreen : Screen("characters")

    /**
     * Represents the screen displaying details of a specific character.
     * The route string should be used for navigation without parameters.
     */
    object CharacterDetailScreen : Screen("characterDetail")
}
