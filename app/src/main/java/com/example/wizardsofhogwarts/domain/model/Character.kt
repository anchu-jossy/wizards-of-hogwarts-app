package com.example.wizardsofhogwarts.domain.model

data class Character(
    val id: String,
    val name: String,
    val actor: String,
    val species: String,
    val house: String,
    val dateOfBirth: String,
    val alive: Boolean,
    val image: String,

){

    fun doesMatchSearchQuery(query: String): Boolean {
        val doesMatch= listOf(name, actor)
        return doesMatch.any {it.contains(query, ignoreCase = true)  }
    }
}



