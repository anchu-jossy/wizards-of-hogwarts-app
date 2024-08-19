package com.example.wizardsofhogwarts.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.edit

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map


class ThemePreferenceManager(private val dataStore: DataStore<Preferences>) {

    companion object {
        private val THEME_MODE_KEY = booleanPreferencesKey("theme_mode")
    }

    val themeFlow: Flow<Boolean> = dataStore.data
        .catch { emit(emptyPreferences()) }
        .map { preferences ->
            preferences[THEME_MODE_KEY] ?: false
        }

    suspend fun saveTheme(isDarkMode: Boolean) {
        dataStore.edit { preferences ->
            preferences[THEME_MODE_KEY] = isDarkMode
        }
    }
}
