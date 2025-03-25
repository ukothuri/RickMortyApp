
package com.example.rickmortyapp.viewmodel

import app.cash.turbine.test
import com.example.rickmortyapp.data.model.Character
import com.example.rickmortyapp.data.model.CharacterUiState
import com.example.rickmortyapp.data.repository.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterViewModelTest {

    private lateinit var viewModel: CharacterViewModel
    private lateinit var testRepository: CharacterRepository
    private val testDispatcher = StandardTestDispatcher()

    private val testCharacters = listOf(
        Character(1, "Rick Sanchez", "https://rick.image"),
        Character(2, "Morty Smith", "https://morty.image")
    )

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        testRepository = object : CharacterRepository {
            override suspend fun fetchCharacters() = testCharacters
        }
        viewModel = CharacterViewModel(testRepository,testDispatcher)
    }

    @Test
    fun `fetchCharacters should emit loading and then success state`() = runTest {
        viewModel.fetchCharacters()

        viewModel.uiState.test {
            val loading = awaitItem()
            assert(loading is CharacterUiState.Loading)

            testDispatcher.scheduler.advanceUntilIdle()

            val success = awaitItem()
            assert(success is CharacterUiState.Success)
            assertEquals(testCharacters, (success as CharacterUiState.Success).characters)
        }
    }


    @Test
    fun `selectCharacter should update selectedCharacter`() = runTest {
        viewModel.selectCharacter(testCharacters[0])
        assertEquals(testCharacters[0], viewModel.selectedCharacter.value)
    }

    @Test
    fun `clearSelectedCharacter should reset selectedCharacter`() = runTest {
        viewModel.selectCharacter(testCharacters[0])
        viewModel.clearSelectedCharacter()
        assertEquals(null, viewModel.selectedCharacter.value)
    }
}
