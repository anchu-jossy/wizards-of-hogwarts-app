package com.example.wizardsofhogwarts.ui.characterlist

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.LazyPagingItems
import com.example.wizardsofhogwarts.domain.model.Character
import com.example.wizardsofhogwarts.ui.navigation.Screen
import com.example.wizardsofhogwarts.ui.utils.getHouseColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListScreen(
    navController: NavController
) {
    val viewModel: CharacterListViewModel = hiltViewModel()
    // Collect the paging data as lazy paging items
    val characterPagingItems: LazyPagingItems<Character> =
        viewModel.characterList.collectAsLazyPagingItems()


    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {

        items(characterPagingItems.itemCount) { index ->
            characterPagingItems[index].let {
                it?.let { it1 ->
                    CharacterListItem(character = it1, onItemClick = { character ->
                        // Navigate to the character detail screen with the character ID as an argument
                        character.id.let { characterId -> navController.navigate(Screen.CharacterDetailScreen.route + "/$characterId") }

                    })
                }
            }

        }
    }
}

@Composable
fun CharacterListItem(character: Character, onItemClick: (Character) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
                onClick = {
            onItemClick(character)
        },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min) // Ensures Row expands to fit content
        ) {
            // Color bar on the left side
            Box(
                modifier = Modifier

                    .fillMaxHeight() // Fill the height of the card
                    .width(8.dp) // Width of the color bar
                    .background(getHouseColor(character.house)) // Background color of Box
            )

            Spacer(modifier = Modifier.width(8.dp)) // Space between color bar and content

            // Content of the card
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp) // Padding around the content
            ) {
                Text(
                    text = character.name,
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    text = "Actor: ${character.actor}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Species: ${character.species}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

