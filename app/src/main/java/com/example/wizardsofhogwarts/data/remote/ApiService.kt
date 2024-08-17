package com.example.wizardsofhogwarts.data.remote

import com.example.wizardsofhogwarts.data.remote.dto.CharacterDTO
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("api/characters/")
    suspend fun getCharacterList(   @Query("page") page: Int,
                                    @Query("pageSize") pageSize: Int): List<CharacterDTO>

    companion object {
        const val BASE_URL = "https://hp-api.onrender.com/"
    }
}