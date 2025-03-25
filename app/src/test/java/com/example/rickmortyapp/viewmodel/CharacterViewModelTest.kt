
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
        viewModel = CharacterViewModel(
            repository = object : CharacterRepository {
                override suspend fun fetchCharacters(): List<Character> = testCharacters
            },
            dispatcher = testDispatcher // 👈 make sure this matches
        )
    }


    @Test
    fun `retry should emit loading and then success state`() = runTest {
        viewModel.retry() // Trigger the data load

        viewModel.uiState.test {
            val loading = awaitItem()
            println("🌀 Loading emitted: $loading")
            assert(loading is CharacterUiState.Loading)

            testDispatcher.scheduler.advanceUntilIdle()

            val success = awaitItem()
            println("✅ Success emitted: $success")
            assert(success is CharacterUiState.Success)
            assertEquals(testCharacters, (success as CharacterUiState.Success).characters)

            cancelAndIgnoreRemainingEvents()
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
