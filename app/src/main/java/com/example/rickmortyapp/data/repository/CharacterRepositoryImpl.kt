package com.example.rickmortyapp.data.repository

import com.example.rickmortyapp.data.model.Character
import com.example.rickmortyapp.data.remote.RetrofitInstance

class CharacterRepositoryImpl : CharacterRepository {
    override suspend fun fetchCharacters(): List<Character> {
        return RetrofitInstance.api.getCharacters().results
    }
}