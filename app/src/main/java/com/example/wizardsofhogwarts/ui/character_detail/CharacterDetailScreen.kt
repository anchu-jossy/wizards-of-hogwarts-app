package com.example.wizardsofhogwarts.ui.character_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.wizardsofhogwarts.ui.characterlist.CharacterListViewModel
import com.example.wizardsofhogwarts.ui.utils.getHouseColor

@Composable
fun CharacterDetailScreen(viewModel: CharacterListViewModel) {

    val character = viewModel.selectedCharacter.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            contentAlignment = Alignment.Center,
        ) {
            AsyncImage(
                model = character?.image,
                contentDescription = null, modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
                    .border(
                        10.dp,
                        color = getHouseColor(character?.house ?: ""),
                        shape = CircleShape
                    )
                    .background(Color.Gray),
                contentScale = ContentScale.Crop

            )
        }
        Column(modifier = Modifier.padding(20.dp), horizontalAlignment = Alignment.Start) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Character Details",
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = "Name :${character?.name}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Actor: ${character?.actor}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Species: ${character?.species}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Date Of Birth: ${character?.dateOfBirth}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(text = "isAlive: ${character?.alive}", style = MaterialTheme.typography.bodyMedium)

        }
    }

}
