package com.example.wizardsofhogwarts.presentation.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.wizardsofhogwarts.R
import com.example.wizardsofhogwarts.domain.model.Character
import com.example.wizardsofhogwarts.presentation.utils.getHouseColor

/**
 * Composable function to display basic details of a character.
 * Optionally displays additional information if requested.
 *
 * @param character The [Character] object containing details to display.
 * @param isAdditonalInfoNeeded Boolean flag indicating if additional information should be shown.
 * @param modifier [Modifier] to apply styling or layout adjustments to the Text elements.
 */
@Composable
fun TextWithBasicDetails(
    character: Character,
    isAdditonalInfoNeeded: Boolean,
    modifier: Modifier
) {
    // Display the character's name with a dynamic color based on their house
    Text(
        modifier = modifier,
        text = character.name,
        style = MaterialTheme.typography.titleLarge,
        color = if (character.house.isNotEmpty()) getHouseColor(character.house) else MaterialTheme.colorScheme.primary
    )

    // Display the actor's name with a localized label
    Text(
        modifier = modifier,
        text = stringResource(id = R.string.actor_label, character.actor),
        style = MaterialTheme.typography.bodyLarge
    )

    // Display the species with a localized label
    Text(
        modifier = modifier,
        text = stringResource(id = R.string.species_label, character.species),
        style = MaterialTheme.typography.bodyLarge
    )

    // Conditionally display additional information if requested
    if (isAdditonalInfoNeeded) {
        Text(
            modifier = modifier,
            text = stringResource(id = R.string.date_of_birth_label, character.dateOfBirth),
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            modifier = modifier,
            text = stringResource(id = R.string.is_alive_label, character.alive.toString()),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
