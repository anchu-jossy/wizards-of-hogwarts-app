package com.example.wizardsofhogwarts.domain.usecases

import com.example.wizardsofhogwarts.data.datastore.ThemePreferenceManager
import javax.inject.Inject

class SetThemeUseCase @Inject constructor(private val themePreferenceManager: ThemePreferenceManager) {
    suspend operator fun invoke(isDarkMode: Boolean) {
        themePreferenceManager.saveTheme(isDarkMode)
    }
}