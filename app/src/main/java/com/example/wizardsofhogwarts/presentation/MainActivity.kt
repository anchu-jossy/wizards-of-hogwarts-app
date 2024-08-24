package com.example.wizardsofhogwarts.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.wizardsofhogwarts.R
import com.example.wizardsofhogwarts.presentation.characterlist.CharacterListViewModel
import com.example.wizardsofhogwarts.presentation.component.AppBarTitleAndDrawer
import com.example.wizardsofhogwarts.presentation.component.ModelNavDrawer
import com.example.wizardsofhogwarts.presentation.navigation.Navigation
import com.example.wizardsofhogwarts.presentation.navigation.Screen
import com.example.wizardsofhogwarts.presentation.theme.ThemeSwitcherTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Initialize navigation controller and drawer state
            val navController = rememberNavController()
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val scope = rememberCoroutineScope()
            // Obtain the view model using Hilt
            val viewModel: CharacterListViewModel = hiltViewModel()
            // Observe theme state from the view model
            val darkTheme by viewModel.theme.collectAsState()

            // Apply the selected theme
            ThemeSwitcherTheme(darkTheme = darkTheme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ModelNavDrawer(drawerContent = {
                        SetDrawerContent(darkTheme = darkTheme, viewModel = viewModel)
                    }, drawerState = drawerState, mainContent = {
                        MainScreenContent(
                            scope = scope,
                            drawerState = drawerState,
                            navController = navController,
                            viewModel = viewModel
                        )
                    })

                }
            }
        }
    }
}

@Composable
fun ThemeSwitcherText(darkTheme: Boolean) {
    // Display the text for switching themes
    Text(
        text = if (darkTheme)
            stringResource(id = R.string.light_theme)
        else
            stringResource(id = R.string.dark_theme),
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun SetDrawerContent(darkTheme: Boolean, viewModel: CharacterListViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.6f) // Control the width of the drawer
            .fillMaxHeight()
    ) {
        ModalDrawerSheet {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ThemeSwitcherText(darkTheme = darkTheme)
                Switch(
                    checked = darkTheme,
                    onCheckedChange = {
                        // Toggle theme in the view model
                        viewModel.toggleTheme(it)
                    }
                )
            }
        }
    }
}

@Composable
fun MainScreenContent(
    scope: CoroutineScope,
    drawerState: DrawerState,
    navController: NavHostController,
    viewModel: CharacterListViewModel
) {
    Scaffold(
        topBar = {
            // App bar with drawer icon
            AppBarTitleAndDrawer(
                title = stringResource(id = R.string.app_name),
                onDrawerIconClick = {
                    scope.launch {
                        drawerState.open()
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            // Collect state and data from the view model
            val state by viewModel.state.collectAsState()
            val characterList by viewModel.characterList.collectAsState()
            val searchText by viewModel.searchText.collectAsState()
            // Navigation for the app
            Navigation(
                navController = navController,
                viewModel = viewModel,
                onItemClick = { clickedItem ->
                    viewModel.addCharacter(clickedItem)
                    navController.navigate(Screen.CharacterDetailScreen.route)
                },
                onSearchTextChange = {
                    viewModel.onSearchTextChange(it)
                },
                state = state,
                characterList = characterList,
                searchText = searchText
            )
        }
    }
}
