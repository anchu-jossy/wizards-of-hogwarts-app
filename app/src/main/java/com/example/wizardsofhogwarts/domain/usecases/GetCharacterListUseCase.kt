package com.example.wizardsofhogwarts.domain.usecases

import com.example.wizardsofhogwarts.domain.model.Character
import com.example.wizardsofhogwarts.domain.repository.CharacterRepository
import com.example.wizardsofhogwarts.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for retrieving the list of characters.
 *
 * This class is responsible for encapsulating the logic to fetch character data
 * through the [CharacterRepository]. It handles the interaction with the repository
 * and exposes the data wrapped in a [Resource] object.
 *
 * @property characterRepository The repository used to fetch character data.
 */
class GetCharacterListUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {

    /**
     * Executes the use case to retrieve the list of characters.
     *
     * This method interacts with the [CharacterRepository] to get the character list
     * and returns it wrapped in a [Resource] object. The [Resource] object helps in
     * managing the state of data retrieval, which could be loading, success, or error.
     *
     * @return A [Flow] emitting [Resource] containing a list of [Character] objects.
     */
    fun execute(): Flow<Resource<List<Character>>> {
        return characterRepository.getCharacterList()
    }
}
