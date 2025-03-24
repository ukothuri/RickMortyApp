package com.example.rickmortyapp.repository

import com.example.rickmortyapp.data.remote.RetrofitInstance
import com.example.rickmortyapp.data.model.Character

class CharacterRepository {
    suspend fun fetchCharacters(): List<Character> {
        return RetrofitInstance.api.getCharacters().results
    }
}