package com.example.wizardsofhogwarts.data.locals.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a data entity for a character in the Hogwarts universe.
 *
 * This class defines the schema of the 'characters' table in the Room database, including all fields
 * associated with a character's details. Each field maps to a column in the table.
 *
 * @property id Unique identifier for the character. This is the primary key for the 'characters' table.
 * @property name Name of the character.
 * @property actor Actor who portrayed the character.
 * @property species Species of the character (e.g., human).
 * @property image URL or path to the character's image.
 * @property house The house to which the character belongs (e.g., Gryffindor, Slytherin).
 * @property alive Indicates whether the character is alive or not.
 * @property dateOfBirth The birthdate of the character.
 */
@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey val id: String,        // Unique identifier for the character (Primary Key).
    val name: String,                 // Name of the character.
    val actor: String,                // Actor who portrayed the character.
    val species: String,              // Species of the character.
    val image: String,                // URL or path to the character's image.
    val house: String,                // House of the character.
    val alive: Boolean,               // Whether the character is alive or not.
    val dateOfBirth: String           // Date of birth of the character.
)
