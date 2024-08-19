package com.example.wizardsofhogwarts.domain.usecases

import com.example.wizardsofhogwarts.domain.model.Character
import com.example.wizardsofhogwarts.domain.repository.CharacterRepository
import com.example.wizardsofhogwarts.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterListUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {
    fun execute(): Flow<Resource<List<Character>>> {
        return characterRepository.getCharacterList()
    }
}
