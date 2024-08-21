package com.example.wizardsofhogwarts.presentation.character_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.wizardsofhogwarts.R
import com.example.wizardsofhogwarts.domain.model.Character
import com.example.wizardsofhogwarts.presentation.shared.CharacterListViewModel
import com.example.wizardsofhogwarts.presentation.component.TextWithBasicDetails
import com.example.wizardsofhogwarts.presentation.utils.getHouseColor

/**
 * Composable function to display the details of a character.
 *
 * @param viewModel The view model that provides the character data.
 */
@Composable
fun CharacterDetailScreen(viewModel: CharacterListViewModel) {
    // Collect the selected character state from the view model
    val character by viewModel.selectedCharacter.collectAsState()

    // Main container for the screen
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .verticalScroll(rememberScrollState()), // Allows vertical scrolling
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CharacterImage(character = character)
        Spacer(modifier = Modifier.height(16.dp)) // Space between image and details
        CharacterDetails(character = character)
    }
}

/**
 * Composable function to display the character's image.
 *
 * @param character The character whose image is to be displayed.
 */
@Composable
fun CharacterImage(character: Character) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .aspectRatio(1f), // Maintains a 1:1 aspect ratio
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = character.image.takeIf { it.isNotEmpty() } ?: R.drawable.ic_user,
            contentDescription = stringResource(R.string.character_image_description),
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape) // Clips image to a circular shape
                .border(
                    width = 10.dp,
                    color = getHouseColor(character.house),
                    shape = CircleShape // Applies border with circular shape
                ),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(R.drawable.ic_user),
            error = painterResource(R.drawable.ic_user)
        )
    }
}

/**
 * Composable function to display the character's details.
 *
 * @param character The character whose details are to be displayed.
 */
@Composable
fun CharacterDetails(character: Character) {
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextWithBasicDetails(
            character = character,
            isAdditonalInfoNeeded = true, // Flag to show additional information
            modifier = Modifier
                .align(Alignment.CenterHorizontally) // Centers the text horizontally
        )
    }
}
