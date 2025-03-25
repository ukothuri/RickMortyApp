package com.example.rickmortyapp.data.repository
import com.example.rickmortyapp.data.model.Character

interface CharacterRepository {
    suspend fun fetchCharacters(): List<Character>
}
