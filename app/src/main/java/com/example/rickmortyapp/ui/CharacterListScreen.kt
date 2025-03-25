package com.example.rickmortyapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.rickmortyapp.data.model.Character
import com.example.rickmortyapp.data.model.CharacterUiState
import com.example.rickmortyapp.data.repository.MockCharacterRepositoryImpl
import com.example.rickmortyapp.viewmodel.CharacterViewModel
import kotlinx.coroutines.Dispatchers

@Composable
fun CharacterListScreen(
    viewModel: CharacterViewModel,
    onItemClick: (Character) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()


    Box(modifier = Modifier.fillMaxSize()) {
        when (val state = uiState) {
            is CharacterUiState.Loading -> CircularProgressIndicator()
            is CharacterUiState.Success -> {
                val characters = (uiState as CharacterUiState.Success).characters
                LazyColumn {
                    items(characters) { character ->
                        CharacterItem(character = character) {
                            onItemClick(character)
                        }
                    }
                }
            }
            is CharacterUiState.Error -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("Oops! ${state.message}")
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = { viewModel.retry() }) {
                        Text("Retry")
                    }
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

@Preview(showBackground = true)
@Composable
fun CharacterListPreview() {
    // Using a fake/mock repository for preview
    val fakeRepo = MockCharacterRepositoryImpl()

    // Using Unconfined dispatcher for preview (safe for Compose previews)
    val viewModel = remember {
        CharacterViewModel(
            repository = fakeRepo,
            dispatcher = Dispatchers.Unconfined
        )
    }

    // You might want to trigger retry() to populate initial state for preview
    LaunchedEffect(Unit) {
        viewModel.retry()
    }

    CharacterListScreen(
        viewModel = viewModel,
        onItemClick = {}
    )
}


