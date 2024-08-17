//package com.example.wizardsofhogwarts.data.locals.dao
//
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//
//@Dao
//interface CharacterDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertWordInfo(list: List<WordInfoEntity>)
//
//    @Query("DELETE FROM wordinfoentity WHERE word IN(:list)")
//    suspend fun deleteWordInfo(list: List<String>)
//
//    @Query("SELECT * FROM wordinfoentity WHERE word LIKE '%' || :word || '%'")
//    suspend fun getWordInfos(word: String):List<WordInfoEntity>
//}