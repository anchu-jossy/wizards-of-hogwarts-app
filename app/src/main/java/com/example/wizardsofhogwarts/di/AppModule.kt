package com.example.wizardsofhogwarts.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.example.wizardsofhogwarts.data.datastore.ThemePreferenceManager
import com.example.wizardsofhogwarts.data.locals.dao.CharacterDao
import com.example.wizardsofhogwarts.data.locals.database.CharacterDatabase
import com.example.wizardsofhogwarts.data.remote.ApiService
import com.example.wizardsofhogwarts.data.repository.CharacterRepositoryImpl
import com.example.wizardsofhogwarts.domain.repository.CharacterRepository
import com.example.wizardsofhogwarts.domain.usecases.GetCharacterListUseCase

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideCharacterUseCase(repository: CharacterRepository): GetCharacterListUseCase {
        return GetCharacterListUseCase(repository)
    }


    @Provides
    @Singleton
    fun provideCharacterRepository(

        api: ApiService,characterDao: CharacterDao
    ): CharacterRepository {
        return CharacterRepositoryImpl(api,characterDao )
    }

    @Provides
    @Singleton
    fun provideCharacterApi(): ApiService {
        return Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideThemePreferenceManager(@ApplicationContext context: Context): ThemePreferenceManager {
        return ThemePreferenceManager(context.dataStore)
    }



    @Provides
    @Singleton
    fun provideCharacterDatabase(@ApplicationContext context: Context): CharacterDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            CharacterDatabase::class.java,
            "character_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCharacterDao(database: CharacterDatabase): CharacterDao {
        return database.characterDao()
    }

}

