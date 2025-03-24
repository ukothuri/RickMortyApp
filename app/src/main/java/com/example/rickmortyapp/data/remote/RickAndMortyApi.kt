package com.example.rickmortyapp.data.remote

import com.example.rickmortyapp.data.model.CharacterResponse
import retrofit2.http.GET

interface RickAndMortyApi {
    @GET("character")
    suspend fun getCharacters(): CharacterResponse
}