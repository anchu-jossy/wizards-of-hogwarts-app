package com.example.wizardsofhogwarts.ui.characterlist

import com.example.wizardsofhogwarts.domain.model.Character

data class CharacterListState( val charaterItems: List<Character> = emptyList(),
                           val isLoading: Boolean = false, val error: String = ""
)
