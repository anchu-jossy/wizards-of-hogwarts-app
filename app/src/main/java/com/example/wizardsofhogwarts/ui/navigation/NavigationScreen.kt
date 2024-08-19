package com.example.wizardsofhogwarts.ui.navigation

import androidx.annotation.StringRes
import com.example.wizardsofhogwarts.R

sealed class Screen(
    val route: String,

    ) {

    object CharactersListScreen : Screen("characters")
    object CharacterDetailScreen : Screen("id")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { it ->
                append("/$it")
            }
        }

    }
}