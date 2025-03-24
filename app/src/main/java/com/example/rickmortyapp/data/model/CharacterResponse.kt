package com.example.rickmortyapp.data.model

data class CharacterResponse(
    val results: List<Character>
)

data class Character(
    val id: Int,
    val name: String,
    val image: String
)