// MockCharacterRepositoryImpl.kt
package com.example.rickmortyapp.data.repository

import com.example.rickmortyapp.data.model.Character

class MockCharacterRepositoryImpl : CharacterRepository {
    override suspend fun fetchCharacters(): List<Character> {
        return listOf(
            Character(id = 1, name = "Rick Sanchez", image = "https://rick.image"),
            Character(id = 2, name = "Morty Smith", image = "https://morty.image"),
            Character(id = 3, name = "Summer Smith", image = "https://summer.image")
        )
    }
}
