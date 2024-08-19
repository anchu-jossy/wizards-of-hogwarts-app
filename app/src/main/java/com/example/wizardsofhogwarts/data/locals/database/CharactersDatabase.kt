package com.example.wizardsofhogwarts.data.locals.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.wizardsofhogwarts.data.local.entity.CharacterEntity
import com.example.wizardsofhogwarts.data.locals.dao.CharacterDao

@Database(entities = [CharacterEntity::class], version = 1)
abstract class CharacterDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao

    companion object {
        @Volatile
         var INSTANCE: CharacterDatabase? = null


    }
}