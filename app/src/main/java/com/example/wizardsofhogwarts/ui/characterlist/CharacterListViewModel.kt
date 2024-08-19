package com.example.wizardsofhogwarts.ui.characterlist

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wizardsofhogwarts.domain.model.Character
import com.example.wizardsofhogwarts.domain.usecases.GetCharacterListUseCase
import com.example.wizardsofhogwarts.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

import com.example.wizardsofhogwarts.domain.usecases.GetThemeUseCase
import com.example.wizardsofhogwarts.domain.usecases.SetThemeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getCharacterListUseCase: GetCharacterListUseCase,
    private val getThemeUseCase: GetThemeUseCase,
    private val setThemeUseCase: SetThemeUseCase
) : ViewModel() {
    private val _searchText = MutableStateFlow("")
    var searchText = _searchText.asStateFlow()
    private val _state = MutableStateFlow(CharacterListState())
    val state = _state.asStateFlow()


    init {
        getTheme()
        getCharacterList()
    }

    val characterList: StateFlow<List<Character>> = searchText
        .combine(state) { text, state ->
            if (text.isBlank()) {
                state.characterItems
            } else {
                state.characterItems.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }.stateIn(
            scope = viewModelScope,           // Assuming viewModelScope is defined in your ViewModel
            started = WhileSubscribed(5000),  // Defines how the flow should be started/stopped
            initialValue = state.value.characterItems  // Initial value of the StateFlow
        )



    var theme = mutableStateOf(false)
    var selectedCharacter = mutableStateOf<Character?>(null)
        private set


    fun addCharacter(character: Character) {
        selectedCharacter.value = character
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    private fun getCharacterList() {
        viewModelScope.launch {
            getCharacterListUseCase.execute().collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(isLoading = true)
                    }

                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            characterItems = result.data ?: emptyList(),
                            isLoading = false
                        )
                    }

                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            error = result.message ?: "An unexpected error occurred",
                            isLoading = false
                        )
                    }
                }
            }
        }
    }


    private fun getTheme() {
        viewModelScope.launch {
            getThemeUseCase().collect { darkMode ->
                theme.value = darkMode
            }
        }
    }

    fun toggleTheme(isDarkMode: Boolean) {
        viewModelScope.launch {
            setThemeUseCase(isDarkMode)
            theme.value = isDarkMode
        }
    }
}
