package com.example.wizardsofhogwarts.domain.usecases

import com.example.wizardsofhogwarts.data.datastore.ThemePreferenceManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for retrieving the current theme preference.
 *
 * This class encapsulates the logic to fetch the current theme mode (dark or light)
 * from the [ThemePreferenceManager]. It provides a clean interface to access the
 * theme preference as a [Flow] of boolean values.
 *
 * @property themePreferenceManager The manager used to access theme preference data.
 */
class GetThemeUseCase @Inject constructor(
    private val themePreferenceManager: ThemePreferenceManager
) {

    /**
     * Retrieves the current theme preference as a [Flow] of [Boolean].
     *
     * This method invokes the [ThemePreferenceManager] to get the theme preference
     * flow. Subscribers to this flow will receive updates whenever the theme preference changes.
     *
     * @return A [Flow] emitting the current theme preference, where [Boolean] indicates
     * whether the dark mode is enabled (`true`) or not (`false`).
     */
    operator fun invoke(): Flow<Boolean> {
        return themePreferenceManager.themeFlow
    }
}
