package com.example.wizardsofhogwarts.data.repository

import com.example.wizardsofhogwarts.data.locals.dao.CharacterDao
import com.example.wizardsofhogwarts.data.mappers.CharacterMapper.toCharacterEntity
import com.example.wizardsofhogwarts.data.remote.ApiService
import com.example.wizardsofhogwarts.data.mappers.CharacterMapper.toDomainModel
import com.example.wizardsofhogwarts.domain.model.Character
import com.example.wizardsofhogwarts.domain.repository.CharacterRepository
import com.example.wizardsofhogwarts.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Implementation of [CharacterRepository] for managing character data from local and remote sources.
 *
 * @param api The [ApiService] used for remote API interactions.
 * @param characterDao The [CharacterDao] used for local database operations.
 */
class CharacterRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val characterDao: CharacterDao
) : CharacterRepository {

    /**
     * Retrieves a list of characters. It first tries to fetch data from the local database.
     * If no data is found, it fetches from the remote API, saves it to the local database, and then returns it.
     *
     * @return A [Flow] emitting [Resource] with the list of [Character] or an error message.
     */
    override fun getCharacterList(): Flow<Resource<List<Character>>> = flow {
        emit(Resource.Loading())
        try {
            // Attempt to get data from the local database
            val offlineCharacterList = characterDao.getAllCharacters().firstOrNull()

            if (!offlineCharacterList.isNullOrEmpty()) {
                emit(Resource.Success(offlineCharacterList.map { it.toDomainModel() }))
            } else {
                val characters = fetchAndSaveCharacters()
                emit(Resource.Success(characters))
            }
        } catch (e: HttpException) {
            emit(Resource.Error("Oops, something went wrong! Please try again later."))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server, check your internet connection."))
        }
    }

    /**
     * Fetches characters from the remote API, saves them to the local database, and returns the list.
     *
     * @return A list of [Character] domain models.
     */
    private suspend fun fetchAndSaveCharacters(): List<Character> {
        val response = api.getCharacterList()

        // Convert API response to domain models and local entities
        val characterList = response.map { it.toDomainModel() }
        val characterEntityList = response.map { it.toCharacterEntity() }

        // Save the characters to the local database
        characterDao.insertAllCharacters(characterEntityList)

        return characterList
    }

}
