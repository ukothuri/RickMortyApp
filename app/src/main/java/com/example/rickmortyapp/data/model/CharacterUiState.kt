package com.example.rickmortyapp.data.model

sealed class CharacterUiState {
    object Loading : CharacterUiState()
    data class Success(val characters: List<Character>) : CharacterUiState()
    data class Error(val message: String) : CharacterUiState()
}

