package com.example.wizardsofhogwarts.data.repository

import com.example.wizardsofhogwarts.data.locals.dao.CharacterDao
import com.example.wizardsofhogwarts.data.locals.entity.CharacterEntity
import com.example.wizardsofhogwarts.data.mappers.CharacterMapper.toCharacterEntity
import com.example.wizardsofhogwarts.data.mappers.CharacterMapper.toDomainModel
import com.example.wizardsofhogwarts.data.remote.ApiService
import com.example.wizardsofhogwarts.data.remote.dto.CharacterDTO
import com.example.wizardsofhogwarts.domain.model.Character
import com.example.wizardsofhogwarts.domain.util.Resource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.whenever
import java.io.IOException
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CharacterRepositoryImplTest {

    private lateinit var characterDao: CharacterDao
    private lateinit var apiService: ApiService
    private lateinit var characterRepositoryImpl: CharacterRepositoryImpl

    private val dummyCharacterEntities = listOf(
        CharacterEntity(
            "1",
            "Harry Potter",
            "Daniel Radcliffe",
            "Human",
            "image_url",
            "Gryffindor",
            true,
            "1980-07-31",
        ),
        CharacterEntity(
            "2",
            "Hermione Granger",
            "Emma Watson",
            "Human",
            "image_url",
            "Gryffindor",
            true,
            "1979-09-19"
        )
    )
    private val dummyCharacters = dummyCharacterEntities.map {
        Character(
            it.id,
            it.name,
            it.actor,
            it.species,
            it.house,
            it.dateOfBirth,
            it.alive,
            it.image
        )
    }

    @Before
    fun setUp() {
        characterDao = mockk()
        apiService = mockk()
        characterRepositoryImpl = CharacterRepositoryImpl(apiService, characterDao)
    }

    @Test
    fun `getCharacterList returns characters from local database when available`() = runTest {
        // Arrange
        coEvery { characterDao.getAllCharacters() } returns flowOf(dummyCharacterEntities)

        // Act
        val flow = characterRepositoryImpl.getCharacterList()

        // Collect the emitted values from the flow
        val result = mutableListOf<Resource<List<Character>>>()
        flow.collect { result.add(it) }

        // Assert
        assertTrue(result[0] is Resource.Loading, "Expected Resource.Loading as first emission")

        // Compare the data inside Resource.Success
        val successResult = result[1] as? Resource.Success
        assertNotNull(successResult)
        assertEquals(
            dummyCharacters,
            successResult?.data,
            "Expected the data in Resource.Success to match the dummyCharacters"
        )
    }

    @Test
    fun `getCharacterList fetches from remote API and saves to local database when local data is empty`() =
        runTest {
            // 1. Define the data returned by the API
            val charactersFromApi = listOf(
                CharacterDTO(
                    actor = "Daniel Radcliffe",
                    alive = true,
                    alternate_actors = emptyList(),
                    alternate_names = listOf(
                        "The Boy Who Lived",
                        "The Chosen One",
                        "Undesirable No. 1",
                        "Potty"
                    ),
                    ancestry = "half-blood",
                    dateOfBirth = "31-07-1980",
                    eyeColour = "green",
                    gender = "male",
                    hairColour = "black",
                    hogwartsStaff = false,
                    house = "Gryffindor",
                    id = "9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8",
                    image = "https://ik.imagekit.io/hpapi/harry.jpg",
                    name = "Harry Potter",
                    species = "human"
                )
            )

            // 2. Map DTOs to domain models and entities
            val charactersDomain = charactersFromApi.map { it.toDomainModel() }
            val characterEntities = charactersFromApi.map { it.toCharacterEntity() }

            // 3. Mock the API response
            coEvery { apiService.getCharacterList() } returns charactersFromApi

            // 4. Mock the DAO response for an empty database
            coEvery { characterDao.getAllCharacters() } returns flow {
                emptyList<Character>()

                // 5. Call the repository method
                val result = characterRepositoryImpl.getCharacterList().first()

                // 6. Verify the method behavior
                assert(result is Resource.Success)
                assert((result as Resource.Success).data == charactersDomain)

                // 7. Verify that data is saved to the local database
                coVerify { characterDao.insertAllCharacters(characterEntities) }
            }
        }

    private fun assertNotNull(value: Any?) {
        assertTrue(value != null, "Value should not be null")
    }
}
