package com.example.wizardsofhogwarts.ui.navigation

import androidx.annotation.StringRes
import com.example.wizardsofhogwarts.R

sealed class Screen(
    val route: String,
    @StringRes val title: Int = R.string.app_name,
    val objectName: String = "",
    val objectPath: String = ""
){


    object CharactersListScreen : Screen("characters")
    object CharacterDetailScreen : Screen("charcterIdDetails", objectName = "charcterId", objectPath = "/{charcterId}" )


}