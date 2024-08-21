package com.example.wizardsofhogwarts.data.mappers

import com.example.wizardsofhogwarts.data.locals.entity.CharacterEntity
import com.example.wizardsofhogwarts.data.remote.dto.CharacterDTO
import com.example.wizardsofhogwarts.domain.model.Character
import java.text.SimpleDateFormat
import java.util.*

/**
 * Object responsible for mapping between different representations of Character data.
 *
 * This includes mapping from DTOs (Data Transfer Objects) to domain models and database entities,
 * as well as formatting date strings.
 */
object CharacterMapper {

    /**
     * Converts a [CharacterDTO] to a [Character] domain model.
     *
     * @return [Character] domain model with values mapped from the DTO.
     */
    fun CharacterDTO.toDomainModel(): Character {
        return Character(
            id = id,
            name = name,
            actor = actor,
            species = species,
            house = house,
            dateOfBirth = dateOfBirth.toFormattedDate(), // Format date string
            alive = alive,
            image = image
        )
    }

    /**
     * Converts a [CharacterEntity] to a [Character] domain model.
     *
     * @return [Character] domain model with values mapped from the entity.
     */
    fun CharacterEntity.toDomainModel(): Character {
        return Character(
            id = id,
            name = name,
            actor = actor,
            species = species,
            house = house,
            dateOfBirth = dateOfBirth, // Date is already formatted
            alive = alive,
            image = image
        )
    }

    /**
     * Converts a [CharacterDTO] to a [CharacterEntity] for database storage.
     *
     * @return [CharacterEntity] mapped from the DTO with a formatted date.
     */
    fun CharacterDTO.toCharacterEntity(): CharacterEntity {
        return CharacterEntity(
            id = id,
            name = name,
            actor = actor,
            species = species,
            house = house,
            dateOfBirth = dateOfBirth.toFormattedDate(), // Format date string for storage
            alive = alive,
            image = image
        )
    }

    /**
     * Extension function to format a nullable date string.
     *
     * This function attempts to parse the date string in "dd-MM-yyyy" format and then
     * converts it to "dd MMM yyyy" format. If parsing fails or the string is null/empty,
     * appropriate default values are returned.
     *
     * @return A formatted date string or a default value if the input is null or invalid.
     */
    private fun String?.toFormattedDate(): String {
        if (this.isNullOrEmpty()) return "Unknown Date" // Default value for null or empty strings

        val inputFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

        return try {
            val date: Date = inputFormat.parse(this) ?: return "Invalid Date" // Handle parsing errors
            outputFormat.format(date)
        } catch (e: Exception) {
            "Error Formatting Date"
        }
    }
}
