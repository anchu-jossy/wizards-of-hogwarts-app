package com.example.wizardsofhogwarts.data.repository// ThemeRepository.kt
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ThemeRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val themePreferenceKey: Preferences.Key<String>
) {

    val themeFlow: Flow<String> = dataStore.data
        .map { preferences ->
            preferences[themePreferenceKey] ?: "light" // Default to light theme
        }

    suspend fun setTheme(theme: String) {
        dataStore.edit { preferences ->

            preferences[themePreferenceKey] = theme
        }
    }
}
