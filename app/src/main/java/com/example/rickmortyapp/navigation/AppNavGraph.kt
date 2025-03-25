package com.example.rickmortyapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.rickmortyapp.CharacterDetailScreen
import com.example.rickmortyapp.HomeScreen
import com.example.rickmortyapp.ui.CharacterListScreen
import com.example.rickmortyapp.viewmodel.CharacterViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController,
    viewModel: CharacterViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                onShowCharacters = {
                    navController.navigate("list")
                }
            )
        }

        composable("list") {
            CharacterListScreen(
                viewModel = viewModel,
                onItemClick = { character ->
                    viewModel.selectCharacter(character)
                    navController.navigate("detail")
                }
            )
        }

        composable("detail") {
            val selectedCharacter by viewModel.selectedCharacter.collectAsState()
            if (selectedCharacter != null) {
                CharacterDetailScreen(
                    character = selectedCharacter!!,
                    onBack = {
                        viewModel.clearSelectedCharacter()
                        navController.popBackStack()
                    }
                )
            } else {
                // Auto pop back if character is null
                LaunchedEffect(Unit) {
                    navController.popBackStack()
                }
            }
        }
    }
}