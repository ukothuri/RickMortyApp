package com.example.rickmortyapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.rickmortyapp.data.model.Character
import com.example.rickmortyapp.viewmodel.CharacterViewModel


@Composable
fun CharacterListScreen(
    viewModel: CharacterViewModel = hiltViewModel(),
    onItemClick: (Character) -> Unit = {}
) {
    val characters by viewModel.characters.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    // ✅ Trigger once when Composable is first launched
    LaunchedEffect(Unit) {
        viewModel.fetchCharacters()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            LazyColumn {
                items(characters) { character ->
                    CharacterItem(character = character, onClick = { onItemClick(character) })
                }
            }
        }
    }
}


@Composable
fun CharacterItem(character: Character, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = character.image),
            contentDescription = character.name,
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = character.name, style = MaterialTheme.typography.titleMedium)
    }
}
