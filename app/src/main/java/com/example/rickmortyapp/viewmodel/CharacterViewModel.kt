package com.example.rickmortyapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmortyapp.data.model.Character
import com.example.rickmortyapp.repository.CharacterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharacterViewModel : ViewModel(){
    private val repository = CharacterRepository()
    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    val characters: StateFlow<List<Character>> = _characters

    init {
     viewModelScope.launch {
         try {
             _characters.value = repository.fetchCharacters()
         } catch ( e: Exception){
             e.printStackTrace()
         }
     }
    }
}
