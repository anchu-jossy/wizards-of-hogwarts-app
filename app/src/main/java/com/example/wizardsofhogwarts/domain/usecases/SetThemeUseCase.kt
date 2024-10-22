package com.example.wizardsofhogwarts.domain.usecases

import com.example.wizardsofhogwarts.data.datastore.ThemePreferenceManager
import javax.inject.Inject

/**
 * Use case for setting the theme preference.
 *
 * This class encapsulates the logic to update the current theme mode (dark or light)
 * by interacting with the [ThemePreferenceManager]. It provides a clean interface to
 * modify the theme preference based on user input.
 *
 * @property themePreferenceManager The manager used to save the theme preference.
 */
class SetThemeUseCase @Inject constructor(
    private val themePreferenceManager: ThemePreferenceManager
) {

    /**
     * Sets the theme preference.
     *
     * This method updates the theme preference in the [ThemePreferenceManager] based on
     * the provided boolean value. If `isDarkMode` is `true`, dark mode is enabled; otherwise,
     * light mode is used.
     *
     * @param isDarkMode A [Boolean] indicating whether dark mode should be enabled (`true`)
     * or disabled (`false`).
     */
    suspend operator fun invoke(isDarkMode: Boolean) {
        themePreferenceManager.saveTheme(isDarkMode)
    }
}
