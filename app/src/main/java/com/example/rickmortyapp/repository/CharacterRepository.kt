package com.example.rickmortyapp.repository

import com.example.rickmortyapp.data.model.Character
import com.example.rickmortyapp.data.remote.RickAndMortyApi

class CharacterRepository( private val api: RickAndMortyApi) {
    suspend fun fetchCharacters(): List<Character> {
        return api.getCharacters().results
    }
}