package com.example.wizardsofhogwarts.data.locals.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.wizardsofhogwarts.data.locals.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) interface for performing operations on the 'characters' table in the local database.
 */
@Dao
interface CharacterDao {

    /**
     * Retrieves all characters from the 'characters' table.
     *
     * This function returns a [Flow] of a list of [CharacterEntity] objects.
     * Using Flow allows for observing data changes in real-time.
     *
     * @return A Flow that emits lists of [CharacterEntity] representing all characters.
     */
    @Query("SELECT * FROM characters")
    fun getAllCharacters(): Flow<List<CharacterEntity>>

    /**
     * Inserts a list of characters into the 'characters' table.
     *
     * If a character with the same primary key already exists, it will be replaced.
     *
     * @param characters A list of [CharacterEntity] objects to be inserted into the database.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCharacters(characters: List<CharacterEntity>)
}
