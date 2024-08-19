package com.example.wizardsofhogwarts.domain.repository

import com.example.wizardsofhogwarts.domain.model.Character
import com.example.wizardsofhogwarts.domain.util.Resource
import kotlinx.coroutines.flow.Flow


interface CharacterRepository {
    fun getCharacterList(): Flow<Resource<List<Character>>>


}