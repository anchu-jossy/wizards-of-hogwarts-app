package com.example.wizardsofhogwarts.di

import com.example.wizardsofhogwarts.data.remote.ApiService
import com.example.wizardsofhogwarts.data.repository.CharacterRepositoryImpl
import com.example.wizardsofhogwarts.domain.repository.CharacterRepository
import com.example.wizardsofhogwarts.domain.usecases.GetCharacterListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {



        @Provides
        @Singleton
        fun provideCharacterUseCase(repository: CharacterRepository): GetCharacterListUseCase {
            return GetCharacterListUseCase(repository)
        }


        @Provides
        @Singleton
        fun provideCharacterRepository(

            api: ApiService,
        ): CharacterRepository {
            return CharacterRepositoryImpl(api)
        }

        @Provides
        @Singleton
        fun provideDictionaryApi(): ApiService {
            return Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }

//
//        @Provides
//        @Singleton
//        fun provideWordInfoDatabase(app: Application): WordInfoDatabase {
//            return Room.databaseBuilder(
//                app, WordInfoDatabase::class.java, "word_db"
//            ).addTypeConverter(Converters(GsonParser(Gson())))
//                .build()
//        }
