package com.example.wizardsofhogwarts.presentation.characterlist

import com.example.wizardsofhogwarts.domain.model.Character

/**
 * Data class representing the state of the character list screen.
 *
 * @param characterItems A list of characters to be displayed in the list.
 * @param isLoading A flag indicating whether data is currently being loaded.
 * @param error An error message to be displayed in case of failure.
 */
data class CharacterListState(
    val characterItems: List<Character> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)
