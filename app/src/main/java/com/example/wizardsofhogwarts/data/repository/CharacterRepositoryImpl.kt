package com.example.wizardsofhogwarts.data.repository

import com.example.wizardsofhogwarts.data.locals.dao.CharacterDao
import com.example.wizardsofhogwarts.data.locals.database.CharacterDatabase
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

class CharacterRepositoryImpl @Inject constructor(
    private val api: ApiService,  private val characterDao: CharacterDao

) : CharacterRepository {

    override fun getCharacterList(): Flow<Resource<List<Character>>> = flow {
        emit(Resource.Loading())
        try {

            val offlineCharacterList = characterDao.getAllCharacters().first()

            if(offlineCharacterList.isNotEmpty()){
                emit(Resource.Success( offlineCharacterList.map {it.toDomainModel()}))
            }
            else {
                val characterList: List<Character> = fetchAndSaveCharacters()
                // Emit success with the mapped list
                emit(Resource.Success(characterList))
            }
        } catch (e: HttpException) {
            // Handle HTTP errors
            emit(
                Resource.Error(
                    message = "Oops, something went wrong! Please try again later."
                )
            )
        } catch (e: IOException) {
            // Handle IO errors
            emit(
                Resource.Error(
                    message = "Couldn't reach server, check your internet connection."
                )
            )
        }
    }


    private suspend fun fetchAndSaveCharacters(): List<Character> {
        // Fetch the list of characters from the API
        val response = api.getCharacterList()

        // Map the API response to domain models

        val characterList = response.map {
            it.toDomainModel()
        }
        val characterEntityList = response.map {
            it.toCharacterEntity()
        }

        characterDao.insertAllCharacters(characterEntityList)

        return characterList

    }

}

