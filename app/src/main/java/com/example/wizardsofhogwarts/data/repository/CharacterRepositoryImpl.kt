package com.example.wizardsofhogwarts.data.repository

import com.example.wizardsofhogwarts.data.locals.dao.CharacterDao
import com.example.wizardsofhogwarts.data.mappers.CharacterMapper.toCharacterEntity
import com.example.wizardsofhogwarts.data.remote.ApiService
import com.example.wizardsofhogwarts.data.mappers.CharacterMapper.toDomainModel
import com.example.wizardsofhogwarts.domain.model.Character
import com.example.wizardsofhogwarts.domain.repository.CharacterRepository
import com.example.wizardsofhogwarts.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Implementation of [CharacterRepository] that interacts with the local database and remote API.
 *
 * @param api The [ApiService] instance used for fetching character data from the remote API.
 * @param characterDao The [CharacterDao] instance used for accessing and modifying character data in the local database.
 */
class CharacterRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val characterDao: CharacterDao
) : CharacterRepository {

    /**
     * Fetches a list of characters, either from the local database or remote API.
     *
     * This function first tries to fetch characters from the local database. If the database is empty,
     * it fetches the data from the remote API, saves it to the local database, and returns it.
     *
     * @return A [Flow] emitting [Resource] that contains either a list of [Character] or an error.
     */
    override fun getCharacterList(): Flow<Resource<List<Character>>> = flow {
        emit(Resource.Loading())
        try {
            // Fetch characters from local database
            val offlineCharacterList = characterDao.getAllCharacters().first()

            if (offlineCharacterList.isNotEmpty()) {
                // If local data exists, emit it as success
                emit(Resource.Success(offlineCharacterList.map { it.toDomainModel() }))
            } else {
                // Fetch from remote API and save to local database
                val characterList = fetchAndSaveCharacters()
                emit(Resource.Success(characterList))
            }
        } catch (e: HttpException) {
            // Handle HTTP errors
            emit(Resource.Error("Oops, something went wrong! Please try again later."))
        } catch (e: IOException) {
            // Handle IO errors
            emit(Resource.Error("Couldn't reach server, check your internet connection."))
        }
    }

    /**
     * Fetches characters from the remote API and saves them to the local database.
     *
     * @return A list of [Character] domain models.
     */
    private suspend fun fetchAndSaveCharacters(): List<Character> {
        // Fetch character data from the remote API
        val response = api.getCharacterList()

        // Map the API response to domain models and local database entities
        val characterList = response.map { it.toDomainModel() }
        val characterEntityList = response.map { it.toCharacterEntity() }

        // Save characters to the local database
        characterDao.insertAllCharacters(characterEntityList)

        return characterList
    }
}
