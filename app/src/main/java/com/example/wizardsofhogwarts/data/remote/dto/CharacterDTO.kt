package com.example.wizardsofhogwarts.data.remote.dto

data class CharacterDTO(
    val actor: String,
    val alive: Boolean,
    val alternate_actors: List<String>,
    val alternate_names: List<String>,
    val ancestry: String,
    val dateOfBirth: String,
    val eyeColour: String,
    val gender: String,
    val hairColour: String,
    val hogwartsStaff: Boolean,
    val house: String,
    val id: String,
    val image: String,
    val name: String,
    val species: String,
)