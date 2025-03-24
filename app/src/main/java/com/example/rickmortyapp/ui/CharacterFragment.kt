package com.example.rickmortyapp.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.rickmortyapp.viewmodel.CharacterViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CharacterFragment() {
    val viewModel: CharacterViewModel = viewModel()
    MaterialTheme {
        CharacterListScreen(viewModel) { selectedCharacter ->
            println("Character clicked: ${'$'}{selectedCharacter.name}")
        }
    }
}
