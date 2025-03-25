
# Rick & Morty Compose App 🪐

A simple Android app built using Jetpack Compose that displays a list of characters from the Rick and Morty universe.

---

## ✨ Features

- ✅ Jetpack Compose UI
- ✅ ViewModel + StateFlow for state management
- ✅ Character UI state managed via a single `CharacterUiState` class
- ✅ Manual dependency injection (no Hilt)
- ✅ Repository abstraction for data access
- ✅ Clean MVVM architecture
- ✅ Support for mock repository (preview/testing)
- ✅ Kotlin Coroutines for async operations
- ✅ Coil for image loading

---

## 🚀 Getting Started

### Prerequisites

- Android Studio Electric Eel or newer
- Kotlin 1.9+
- Compose Compiler 1.5.3+
- Minimum SDK 24

---

### Run the App

1. Clone the repo:

```bash
git clone https://github.com/your-username/rickmorty-compose-app.git
```

2. Open in Android Studio

3. Run the app on a device or emulator

---

## 🧪 Testing & Preview Support

- The project includes a `MockCharacterRepositoryImpl` used in previews and development.
- If you're writing unit tests, create a `FakeCharacterRepository.kt` under `src/test/`.

---

## 📦 Architecture Overview

- **MVVM**: ViewModel exposes immutable UI state via `StateFlow`
- **UI State**: All screen state is managed with a single `CharacterUiState` class
- **Repository Pattern**: ViewModel depends on `CharacterRepository` interface
- **No Hilt**: ViewModel is manually constructed in Compose using `remember`

---

## 📸 Preview Support

To preview your Composables:

```kotlin
@Preview
@Composable
fun CharacterListPreview() {
    val viewModel = remember { CharacterViewModel(MockCharacterRepositoryImpl()) }

    CharacterListScreen(
        viewModel = viewModel,
        onItemClick = {}
    )
}
```

---

## 🧠 Learnings

- Centralizing state in a `UiState` class simplifies recomposition and testing.
- Separating interface vs. implementation makes the app scalable and testable.
- Avoid putting mock code in production — prefer `src/test/` or `src/debug/`.

---

## 📌 To Do

- [ ] Add real API integration with Retrofit
- [ ] Add pagination with Paging 3
- [ ] Add navigation animations
- [ ] Add empty state / error retry support
- [ ] Add unit tests for ViewModel

---

## 🧑‍💻 Author

Umamaheswara Rao Kothuri  
(Android Developer, Clean Architecture Enthusiast)

---

## 📄 License

This project is licensed under the MIT License.
