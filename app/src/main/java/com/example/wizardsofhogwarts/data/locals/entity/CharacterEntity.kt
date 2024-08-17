package com.example.wizardsofhogwarts.data.locals.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class CharacterInfoEntity(
    val word: String,

    @PrimaryKey val id: Int? = null
) {
//    fun toWordInfo(): WordInfo {
//       return WordInfo(
//            meanings = meanings,
//            word = word,
//            phonetic = phonetic
//        )
//    }
}