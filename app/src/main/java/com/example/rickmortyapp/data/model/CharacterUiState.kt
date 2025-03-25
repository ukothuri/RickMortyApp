package com.example.rickmortyapp.data.model

data class CharacterUiState(
    val isLoading: Boolean = false,
    val characters: List<Character> = emptyList(),
    val errorMessage: String? = null
)
