package com.example.wizardsofhogwarts.domain.model

/**
 * Data class representing a character in the domain layer.
 *
 * @property id Unique identifier for the character.
 * @property name Name of the character.
 * @property actor Actor who played the character.
 * @property species Species of the character.
 * @property house House to which the character belongs.
 * @property dateOfBirth Date of birth of the character.
 * @property alive Whether the character is alive or not.
 * @property image URL or path to the image of the character.
 */
data class Character(
    val id: String = "",
    val name: String = "",
    val actor: String = "",
    val species: String = "",
    val house: String = "",
    val dateOfBirth: String = "",
    val alive: Boolean = true,
    val image: String = ""
) {

    /**
     * Checks if the character matches the provided search query.
     *
     * @param query The search query to match against character's name and actor.
     * @return True if the character's name or actor contains the query string (case-insensitive), otherwise false.
     */
    fun doesMatchSearchQuery(query: String): Boolean {
        val searchableFields = listOf(name, actor)
        return searchableFields.any { it.contains(query, ignoreCase = true) }
    }
}
