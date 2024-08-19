package com.example.wizardsofhogwarts.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey val id: String,
    val name: String,
    val actor: String,
    val species: String,
    val image: String,
    val house: String,
    val alive: Boolean,
    val dateOfBirth: String

)
