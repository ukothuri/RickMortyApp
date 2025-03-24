package com.example.rickmortyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rickmortyapp.ui.CharacterFragment
import com.example.rickmortyapp.ui.CharacterListScreen
import com.example.rickmortyapp.ui.theme.RickMortyAppTheme
import com.example.rickmortyapp.viewmodel.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RickMortyAppTheme {
                val viewModel: CharacterViewModel = hiltViewModel()
                CharacterListScreen(viewModel = viewModel) { character ->
                    // handle character click
                }
            }
        }
    }
}
