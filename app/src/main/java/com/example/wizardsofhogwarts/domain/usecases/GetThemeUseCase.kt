package com.example.wizardsofhogwarts.domain.usecases

import com.example.wizardsofhogwarts.data.datastore.ThemePreferenceManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetThemeUseCase @Inject constructor(private val themePreferenceManager: ThemePreferenceManager) {
    operator fun invoke(): Flow<Boolean> {
        return themePreferenceManager.themeFlow
    }
}