package com.example.wizardsofhogwarts.domain.usecases

import androidx.paging.PagingData
import com.example.wizardsofhogwarts.domain.model.Character
import com.example.wizardsofhogwarts.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterListUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {
    fun execute(): Flow<PagingData<Character>> {
        return characterRepository.getCharacterList()
    }
}
