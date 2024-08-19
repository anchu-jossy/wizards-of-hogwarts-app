package com.example.wizardsofhogwarts.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.wizardsofhogwarts.R

object HogwartColors {
//    val GriffindorColor = Color(0xFF740001)
//    val SlytherinColor = Color(0xFF1a472a)
//    val RavenclawColor = Color(0xFF0c1a40)
//    val HufflepuffColor = Color(0xFFeeb939)
@Composable
    fun griffindorColor(): Color = colorResource(id = R.color.griffindor_color)

    @Composable
    fun slytherinColor(): Color = colorResource(id = R.color.slytherin_color)

    @Composable
    fun ravenclawColor(): Color = colorResource(id = R.color.ravenclaw_color)

    @Composable
    fun hufflepuffColor(): Color = colorResource(id = R.color.hufflepuff_color)
}