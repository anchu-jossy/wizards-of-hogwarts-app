package com.example.wizardsofhogwarts.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

/**
 * Manages theme preference settings using DataStore.
 *
 * @param dataStore The DataStore instance for handling Preferences.
 */
class ThemePreferenceManager(private val dataStore: DataStore<Preferences>) {

    companion object {
        // Key used to store the theme mode preference in DataStore
        private val THEME_MODE_KEY = booleanPreferencesKey("theme_mode")
    }

    /**
     * Flow that emits the current theme mode preference.
     *
     * Collect this flow to observe changes in the theme mode.
     */
    val themeFlow: Flow<Boolean> = dataStore.data
        .catch { emit(emptyPreferences()) } // Handle any errors by emitting empty preferences
        .map { preferences ->
            // Get the current theme mode preference, default to false if not set
            preferences[THEME_MODE_KEY] ?: false
        }

    /**
     * Save the theme mode preference to DataStore.
     *
     * @param isDarkMode Boolean indicating whether the dark mode is enabled.
     */
    suspend fun saveTheme(isDarkMode: Boolean) {
        dataStore.edit { preferences ->
            // Update the theme mode preference
            preferences[THEME_MODE_KEY] = isDarkMode
        }
    }
}
