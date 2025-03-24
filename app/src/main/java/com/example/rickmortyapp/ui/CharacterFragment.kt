package com.example.rickmortyapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.rickmortyapp.viewmodel.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint

// ui/CharacterFragment.kt
@AndroidEntryPoint
class CharacterFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProvider(this)[CharacterViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    CharacterListScreen(viewModel) { selectedCharacter ->
                        // Handle click - update later
                        Log.d("CharacterClicked", "Clicked: ${selectedCharacter.name}")
                    }
                }
            }
        }
    }
}
