package com.example.rickmortyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.rickmortyapp.data.model.Character
import com.example.rickmortyapp.ui.CharacterListScreen
import com.example.rickmortyapp.ui.theme.RickMortyAppTheme
import com.example.rickmortyapp.viewmodel.CharacterViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickMortyAppTheme {
                val viewModel: CharacterViewModel = viewModel()
                var showCharacters by remember { mutableStateOf(false) }
                var selectedCharacter by remember { mutableStateOf<Character?>(null) }

                when {
                    selectedCharacter != null -> {
                        CharacterDetailScreen(
                            character = selectedCharacter!!,
                            onBack = { selectedCharacter = null }
                        )
                    }
                    showCharacters -> {
                        CharacterListScreen(
                            viewModel = viewModel,
                            onItemClick = { character -> selectedCharacter = character }
                        )
                    }
                    else -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(24.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Button(onClick = { showCharacters = true }) {
                                Text("Show Characters")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CharacterDetailScreen(character: Character, onBack: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberAsyncImagePainter(character.image),
                contentDescription = character.name,
                modifier = Modifier
                    .size(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = character.name, style = MaterialTheme.typography.titleLarge)
        }

        Text(
            text = "Back",
            color = Color.Blue,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
                .clickable { onBack() }
        )
    }
}