package com.example.wizardsofhogwarts.domain.repository

import com.example.wizardsofhogwarts.domain.model.Character
import com.example.wizardsofhogwarts.domain.util.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Interface for interacting with character data.
 *
 * This interface defines methods for fetching character data from different sources,
 * such as network or local database.
 */
interface CharacterRepository {

    /**
     * Retrieves a flow of character list wrapped in a [Resource] object.
     *
     * The [Resource] object will indicate the status of the data retrieval,
     * which can be a loading state, success with data, or an error with a message.
     *
     * @return A [Flow] emitting [Resource] containing a list of [Character] objects.
     */
    fun getCharacterList(): Flow<Resource<List<Character>>>
}
