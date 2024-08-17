package com.example.wizardsofhogwarts.ui.utils



import androidx.compose.ui.graphics.Color
import com.example.wizardsofhogwarts.domain.model.Character

fun getHouseColor(house: String): Color {
    return when (house) {
        "Gryffindor" -> HogwartColors.GriffindorColor
        "Slytherin" -> HogwartColors.SlytherinColor
        "Ravenclaw" -> HogwartColors.RavenclawColor
        "Hufflepuff" -> HogwartColors.HufflepuffColor
        else -> Color.Transparent // Default color if house is unknown
    }
}
