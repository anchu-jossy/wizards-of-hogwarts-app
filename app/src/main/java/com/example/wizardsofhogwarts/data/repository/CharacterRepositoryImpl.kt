package com.example.wizardsofhogwarts.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.wizardsofhogwarts.data.remote.ApiService
import com.example.wizardsofhogwarts.data.mappers.CharacterMapper.toDomainModel
import com.example.wizardsofhogwarts.data.paging.CharacterPagingSource
import com.example.wizardsofhogwarts.domain.model.Character
import com.example.wizardsofhogwarts.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val api: ApiService
) : CharacterRepository {

    override fun getCharacterList(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CharacterPagingSource(api) } // Create PagingSource
        ).flow
            .map { pagingData ->
                pagingData.map { dto ->
                    dto.toDomainModel()

                }
            }
    }
}
