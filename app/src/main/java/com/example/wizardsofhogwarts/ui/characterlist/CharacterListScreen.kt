package com.example.wizardsofhogwarts.ui.characterlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mcoeexercise.component.CircularIndeterminateProgressBar
import com.example.wizardsofhogwarts.domain.model.Character
import com.example.wizardsofhogwarts.ui.component.NoDataPlaceHolder
import com.example.wizardsofhogwarts.ui.component.ErrorView
import com.example.wizardsofhogwarts.ui.navigation.Screen
import com.example.wizardsofhogwarts.ui.utils.getHouseColor

@Composable
fun CharacterListScreen(
    navController: NavController,
    viewModel: CharacterListViewModel
) {
    // Observe the state from the ViewModel
    val state by viewModel.state.collectAsState()
    val characterList by viewModel.characterList.collectAsState()
    val searchText by viewModel.searchText.collectAsState()
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        TextField(
            value = searchText,
            onValueChange = viewModel::onSearchTextChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Search") }
        )


        if (state.isLoading) {

            CircularIndeterminateProgressBar(isDisplayed = true)
        } else if (state.error.isNotBlank()) {
            ErrorView(text=state.error,color=MaterialTheme.colorScheme.error)
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                )
            }
        } else {
            if(characterList.isEmpty()){
                NoDataPlaceHolder()
            }
         else{   LazyColumn(
                contentPadding = PaddingValues(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(characterList) { character ->
                    CharacterListItem(character = character, onItemClick = { clickedItem ->

                        viewModel.addCharacter(clickedItem)
                        navController.navigate(Screen.CharacterDetailScreen.route)
                    })
                }
            }}
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


