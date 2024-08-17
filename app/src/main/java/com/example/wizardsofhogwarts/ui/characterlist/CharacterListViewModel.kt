package com.example.wizardsofhogwarts.ui.characterlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.wizardsofhogwarts.domain.model.Character
import com.example.wizardsofhogwarts.domain.usecases.GetCharacterListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getCharacterListUseCase: GetCharacterListUseCase
) : ViewModel() {
//The flow is cached in viewModelScope, helps to avoid memory leaks.
    // Expose the character list as a Flow of PagingData
    val characterList: Flow<PagingData<Character>> = getCharacterListUseCase.execute()
        .cachedIn(viewModelScope) // Caches the flow in the ViewModel scope to prevent reloading
}
