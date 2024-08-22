package com.example.wizardsofhogwarts.presentation.characterlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wizardsofhogwarts.domain.model.Character
import com.example.wizardsofhogwarts.domain.usecases.GetCharacterListUseCase
import com.example.wizardsofhogwarts.domain.usecases.GetThemeUseCase
import com.example.wizardsofhogwarts.domain.usecases.SetThemeUseCase
import com.example.wizardsofhogwarts.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getCharacterListUseCase: GetCharacterListUseCase,
    private val getThemeUseCase: GetThemeUseCase,
    private val setThemeUseCase: SetThemeUseCase
) : ViewModel() {

    // StateFlow for managing the search text entered by the user
    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText.asStateFlow()

    // StateFlow for managing the overall state of the character list
    private val _state = MutableStateFlow(CharacterListState())
    val state: StateFlow<CharacterListState> = _state.asStateFlow()

    // StateFlow for managing the theme (dark mode or light mode)
    private val _theme = MutableStateFlow(false)
    val theme: StateFlow<Boolean> = _theme.asStateFlow()

    // StateFlow for managing the currently selected character
    private val _selectedCharacter = MutableStateFlow(Character())
    val selectedCharacter: StateFlow<Character> = _selectedCharacter.asStateFlow()

    // Derived StateFlow for filtering the character list based on the search text
    val characterList: StateFlow<List<Character>> = _searchText
        .combine(_state) { searchText, state ->
            val characters = state.characterItems
            if (searchText.isBlank()) {
                characters
            } else {
                characters.filter { it.doesMatchSearchQuery(searchText) }
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Initialize the ViewModel by loading the theme and character list
    init {
        loadTheme()
        loadCharacterList()
    }

    // Sets the selected character
    fun addCharacter(character: Character) {
        _selectedCharacter.value = character
    }

    // Updates the search text
    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    // Fetches the character list from the use case and updates the state
    fun loadCharacterList() {
        viewModelScope.launch {
            getCharacterListUseCase.execute().collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.update { it.copy(isLoading = true) }
                    }
                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                characterItems = result.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                    }
                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                error = result.message ?: "An unexpected error occurred",
                                isLoading = false
                            )
                        }
                    }
                }
            }
        }
    }

    // Fetches the current theme setting from the use case and updates the state
    fun loadTheme() {
        viewModelScope.launch {
            getThemeUseCase().collect { darkMode ->
                _theme.value = darkMode
            }
        }
    }

    // Toggles the theme setting and updates the state
    fun toggleTheme(isDarkMode: Boolean) {
        viewModelScope.launch {
            setThemeUseCase(isDarkMode)
            _theme.value = isDarkMode
        }
    }
}
