package com.example.wizardsofhogwarts.ui.characterlist

import android.content.res.Resources
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wizardsofhogwarts.domain.model.Character
import com.example.wizardsofhogwarts.domain.usecases.GetCharacterListUseCase
import com.example.wizardsofhogwarts.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.State

import com.example.wizardsofhogwarts.domain.usecases.GetThemeUseCase
import com.example.wizardsofhogwarts.domain.usecases.SetThemeUseCase

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getCharacterListUseCase: GetCharacterListUseCase,
    private val getThemeUseCase: GetThemeUseCase,
    private val setThemeUseCase: SetThemeUseCase
) : ViewModel() {

    private val _state = mutableStateOf(CharacterListState())
    val state: State<CharacterListState> = _state
    var theme = mutableStateOf(false)
    var selectedCharacter = mutableStateOf<Character?>(null)
        private set


    init {
        getTheme()
        getCharacterList()
    }

    fun addCharacter(character: Character) {
        selectedCharacter.value = character
    }

    fun getCharacterList() {
        viewModelScope.launch {
            getCharacterListUseCase.execute().collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(isLoading = true)
                    }

                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            charaterItems = result.data ?: emptyList(),
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


  private  fun getTheme() {
        viewModelScope.launch {
            getThemeUseCase().collect {darkMode ->
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
