package com.example.rickmortyapp.repository

import com.example.rickmortyapp.data.model.Character
import com.example.rickmortyapp.data.remote.RetrofitInstance

class CharacterRepository {
    suspend fun fetchCharacters(): List<Character> {
        return RetrofitInstance.api.getCharacters().results
    }
}