package com.example.wizardsofhogwarts.data.mappers

import com.example.wizardsofhogwarts.data.local.entity.CharacterEntity
import com.example.wizardsofhogwarts.data.remote.dto.CharacterDTO
import com.example.wizardsofhogwarts.domain.model.Character
import java.text.SimpleDateFormat
import java.util.*

object CharacterMapper {

    // Extension function to convert CharacterDTO to Character domain model
    fun CharacterDTO.toDomainModel(): Character {
        return Character(
            id = id,
            name = name,
            actor = actor,
            species = species,
            house = house,
            dateOfBirth = dateOfBirth.toFormattedDate(),
            alive = alive,
            image = image,

            )
    }
    fun CharacterEntity.toDomainModel(): Character {
        return Character(
            id = id,
            name = name,
            actor = actor,
            species = species,
            house = house,
            dateOfBirth = dateOfBirth,
            alive = alive,
            image = image
        )
    }

    fun CharacterDTO.toCharacterEntity(): CharacterEntity {
        return CharacterEntity(
            id = id,
            name = name,
            actor = actor,
            species = species,
            house = house,
            dateOfBirth = dateOfBirth.toFormattedDate(),
            alive = alive,
            image = image
        )
    }


    // Extension function to format a nullable date string
    fun String?.toFormattedDate(): String {
        if (this.isNullOrEmpty()) return "Unknown Date" // Default value for null or empty strings

        val inputFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

        return try {
            val date: Date =
                inputFormat.parse(this) ?: return "Invalid Date" // Handle parsing errors
            outputFormat.format(date)
        } catch (e: Exception) {
            "Error Formatting Date"
        }
    }


}
