package com.example.wizardsofhogwarts.presentation.character_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
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
import com.example.wizardsofhogwarts.presentation.component.CharacterDetails
import com.example.wizardsofhogwarts.presentation.utils.getHouseColor

/**
 * Composable function to display the details of a character.
 *
 * @param character The character whose details are to be displayed.
 */
@Composable
fun CharacterDetailScreen(character: Character) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CharacterImage(character = character)
        Spacer(modifier = Modifier.height(16.dp))
        CharacterDetails(
            character = character,
            isAdditonalInfoNeeded = true
        )

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
            .aspectRatio(1f),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = character.image.takeIf { it.isNotEmpty() } ?: R.drawable.ic_user,
            contentDescription = stringResource(R.string.character_image_description),
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
                .border(
                    width = 10.dp,
                    color = getHouseColor(character.house),
                    shape = CircleShape
                ),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(R.drawable.ic_user),
            error = painterResource(R.drawable.ic_user)
        )
    }
}


