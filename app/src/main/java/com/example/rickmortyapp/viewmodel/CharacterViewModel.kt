
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
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CharacterViewModel(
    private val repository: CharacterRepository,
    private val dispatcher: CoroutineDispatcher =  Dispatchers.IO
) : ViewModel() {

    private val _uiState = MutableStateFlow<CharacterUiState>(CharacterUiState.Loading)
    val uiState: StateFlow<CharacterUiState> = _uiState.asStateFlow()

    private val _selectedCharacter = MutableStateFlow<Character?>(null)
    val selectedCharacter: StateFlow<Character?> = _selectedCharacter

    // Retry trigger
    private val retryTrigger = MutableStateFlow(Unit)

    init {
        observeCharacters()
    }

    private fun observeCharacters() {
        retryTrigger
            .onEach {
                _uiState.value = CharacterUiState.Loading

                // ⏳ simulate delay or run fetch on dispatcher
                val characters = repository.fetchCharacters()
                _uiState.value = CharacterUiState.Success(characters)
            }
            .flowOn(dispatcher) // Runs on the background thread
            .launchIn(viewModelScope) //so it’s tied to the ViewModel lifecycle collects on Main
    }


    fun retry() {
        retryTrigger.value = Unit
    }

    fun selectCharacter(character: Character) {
        _selectedCharacter.value = character
    }

    fun clearSelectedCharacter() {
        _selectedCharacter.value = null
    }
}
