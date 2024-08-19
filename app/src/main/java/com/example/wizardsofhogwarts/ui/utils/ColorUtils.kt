package com.example.wizardsofhogwarts.ui.utils


import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.example.wizardsofhogwarts.R

@Composable
fun getHouseColor(house: String): Color {
    return when (house) {
        stringResource(id = R.string.griffindor) -> colorResource(id = R.color.griffindor_color)
        stringResource(id = R.string.slytherin) -> colorResource(id = R.color.slytherin_color)
        stringResource(id = R.string.ravenclaw) -> colorResource(id = R.color.ravenclaw_color)
        stringResource(id = R.string.ravenclaw) -> colorResource(id = R.color.hufflepuff_color)
        else -> Color.Transparent // Default color if house is unknown
    }
}
