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
import com.example.wizardsofhogwarts.domain.model.Character
import com.example.wizardsofhogwarts.presentation.characterlist.CharacterListViewModel
import com.example.wizardsofhogwarts.presentation.component.AppBarTitleAndDrawer
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

            val navController = rememberNavController()
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val scope = rememberCoroutineScope()
            val viewModel: CharacterListViewModel = hiltViewModel()

            val darkTheme by viewModel.theme.collectAsState()

            ThemeSwitcherTheme(darkTheme = darkTheme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ModalNavigationDrawer(
                        drawerContent = {
                            SetDrawerContent(darkTheme = darkTheme, viewModel = viewModel)
                        },
                        drawerState = drawerState
                    ) {
                        MainScreenContent(
                            scope = scope,
                            drawerState = drawerState,
                            navController = navController,
                            viewModel = viewModel
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ThemeSwitcherText(darkTheme: Boolean) {
    Text(
        if (darkTheme)
            stringResource(id = R.string.light_theme) else stringResource(id = R.string.dark_theme),
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun SetDrawerContent(darkTheme: Boolean, viewModel: CharacterListViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.6f) // Adjust this to control the width of the opened drawer
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
            AppBarTitleAndDrawer(
                stringResource(id = R.string.app_name),
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
            val state by viewModel.state.collectAsState()
            val characterList by viewModel.characterList.collectAsState()
            val searchText by viewModel.searchText.collectAsState()
            Navigation(
                navController = navController,
                viewModel = viewModel,
                onItemClick = { clickedItem ->
                    viewModel.addCharacter(clickedItem)
                    navController.navigate(Screen.CharacterDetailScreen.route)
                }, onSearchTextChange = {
                    viewModel.onSearchTextChange(it)
                }, state = state, characterList = characterList, searchText = searchText
            )
        }
    }
}
