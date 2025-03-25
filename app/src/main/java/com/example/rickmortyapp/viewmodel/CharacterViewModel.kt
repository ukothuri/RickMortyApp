package com.example.rickmortyapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmortyapp.data.model.Character
import com.example.rickmortyapp.data.model.CharacterUiState
import com.example.rickmortyapp.data.repository.CharacterRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharacterViewModel( private val repository: CharacterRepository,
                          private val dispatcher: CoroutineDispatcher = Dispatchers.IO) : ViewModel() {




    private val _selectedCharacter = MutableStateFlow<Character?>(null)
    val selectedCharacter: StateFlow<Character?> = _selectedCharacter

    private val _uiState = MutableStateFlow<CharacterUiState>(CharacterUiState.Loading)
    val uiState: StateFlow<CharacterUiState> = _uiState

    fun fetchCharacters() {
        viewModelScope.launch(dispatcher) {
            _uiState.value = CharacterUiState.Loading
            try {
                val result = repository.fetchCharacters()
                _uiState.value = CharacterUiState.Success(result)
            } catch (e: Exception) {
                _uiState.value = CharacterUiState.Error(e.message ?: "Unknown error")
            }
        }
    }


    fun selectCharacter(character: Character) {
        _selectedCharacter.value = character
    }

    fun clearSelectedCharacter() {
        _selectedCharacter.value = null
    }
}
