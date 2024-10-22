package com.example.wizardsofhogwarts.presentation.characterlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.wizardsofhogwarts.domain.model.Character
import com.example.wizardsofhogwarts.presentation.component.CharacterDetails
import com.example.wizardsofhogwarts.presentation.utils.getHouseColor

/**
 * Composable function to display a single character item in the list.
 *
 * @param character The character data to display.
 * @param onItemClick Callback function to handle item click events.
 */
@Composable
fun CharacterListItem(character: Character, onItemClick: (Character) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp) // Space around the card
            .fillMaxWidth(), // Card takes full width of its container
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp), // Card elevation
        onClick = {
            onItemClick(character) // Invoke the callback when the card is clicked
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min) // Row expands to fit the content height
        ) {
            // Left-side color bar
            Box(
                modifier = Modifier
                    .fillMaxHeight() // Fill the height of the card
                    .width(8.dp) // Width of the color bar
                    .background(getHouseColor(character.house)) // Color based on character's house
            )

            Spacer(modifier = Modifier.width(8.dp)) // Space between color bar and content

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp) // Padding around the content within the card
            ) {
                // Character details
                CharacterDetails(
                    character = character,
                    isAdditonalInfoNeeded = false, // No additional information needed
                    modifier = Modifier // Padding below the details
                )
            }
        }
    }
}
