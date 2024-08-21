package com.example.wizardsofhogwarts.data.locals.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.wizardsofhogwarts.data.locals.entity.CharacterEntity
import com.example.wizardsofhogwarts.data.locals.dao.CharacterDao

/**
 * Represents the Room database for the application.
 *
 * This class is responsible for creating and maintaining the database instance for managing 'Character' entities.
 * It extends [RoomDatabase] and provides access to the [CharacterDao] for CRUD operations on 'Character' data.
 *
 * @property characterDao Provides access to the DAO for performing database operations on 'Character' entities.
 */
@Database(entities = [CharacterEntity::class], version = 1, exportSchema = false)
abstract class CharacterDatabase : RoomDatabase() {

    /**
     * Provides access to the [CharacterDao] for interacting with 'Character' data.
     *
     * @return The [CharacterDao] instance associated with this database.
     */
    abstract fun characterDao(): CharacterDao


}
