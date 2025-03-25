package com.example.rickmortyapp.di

import com.example.rickmortyapp.data.remote.RickAndMortyApi
import com.example.rickmortyapp.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): RickAndMortyApi =
        retrofit.create(RickAndMortyApi::class.java)

    @Provides
    @Singleton
    fun provideRepository(api: RickAndMortyApi): CharacterRepository =
        CharacterRepository(api)
}
