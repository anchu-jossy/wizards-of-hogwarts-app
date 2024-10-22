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

/**
 * Dagger Hilt module for providing application-wide dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Extension property to provide DataStore instance
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    /**
     * Provides the [GetCharacterListUseCase] instance.
     *
     * @param repository The [CharacterRepository] instance to be used by the use case.
     * @return A singleton instance of [GetCharacterListUseCase].
     */
    @Provides
    @Singleton
    fun provideCharacterUseCase(repository: CharacterRepository): GetCharacterListUseCase {
        return GetCharacterListUseCase(repository)
    }

    /**
     * Provides the [CharacterRepository] implementation.
     *
     * @param api The [ApiService] instance for making API calls.
     * @param characterDao The [CharacterDao] instance for accessing the local database.
     * @return A singleton instance of [CharacterRepositoryImpl].
     */
    @Provides
    @Singleton
    fun provideCharacterRepository(
        api: ApiService,
        characterDao: CharacterDao
    ): CharacterRepository {
        return CharacterRepositoryImpl(api, characterDao)
    }

    /**
     * Provides the [ApiService] instance for making API calls.
     *
     * @return A singleton instance of [ApiService].
     */
    @Provides
    @Singleton
    fun provideCharacterApi(): ApiService {
        return Retrofit.Builder()
            .baseUrl(com.example.wizardsofhogwarts.BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    /**
     * Provides the [ThemePreferenceManager] instance.
     *
     * @param context The application context used to access DataStore.
     * @return A singleton instance of [ThemePreferenceManager].
     */
    @Provides
    @Singleton
    fun provideThemePreferenceManager(@ApplicationContext context: Context): ThemePreferenceManager {
        return ThemePreferenceManager(context.dataStore)
    }

    /**
     * Provides the [CharacterDatabase] instance.
     *
     * @param context The application context used to create the database.
     * @return A singleton instance of [CharacterDatabase].
     */
    @Provides
    @Singleton
    fun provideCharacterDatabase(@ApplicationContext context: Context): CharacterDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            CharacterDatabase::class.java,
            "character_database"
        ).build()
    }

    /**
     * Provides the [CharacterDao] instance.
     *
     * @param database The [CharacterDatabase] instance used to access the DAO.
     * @return A singleton instance of [CharacterDao].
     */
    @Provides
    @Singleton
    fun provideCharacterDao(database: CharacterDatabase): CharacterDao {
        return database.characterDao()
    }
}
