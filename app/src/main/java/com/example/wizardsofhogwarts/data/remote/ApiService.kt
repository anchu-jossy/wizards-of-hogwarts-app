package com.example.wizardsofhogwarts.data.remote

import com.example.wizardsofhogwarts.data.remote.dto.CharacterDTO
import retrofit2.http.GET

/**
 * API service interface for interacting with the remote API.
 *
 * This interface defines the API endpoints and their respective HTTP methods for accessing character data.
 */
interface ApiService {

    /**
     * Fetches the list of characters from the API.
     *
     * This function makes a GET request to the "api/characters/" endpoint and returns a list of [CharacterDTO] objects.
     *
     * @return A list of [CharacterDTO] representing the characters.
     */
    @GET("api/characters/")
    suspend fun getCharacterList(): List<CharacterDTO>
}
