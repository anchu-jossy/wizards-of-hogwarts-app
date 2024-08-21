package com.example.wizardsofhogwarts.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.wizardsofhogwarts.presentation.component.AppBarTitleAndDrawer
import com.example.wizardsofhogwarts.R
import com.example.wizardsofhogwarts.presentation.shared.CharacterListViewModel
import com.example.wizardsofhogwarts.presentation.navigation.Navigation
import com.example.wizardsofhogwarts.presentation.theme.ThemeSwitcherTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            // Remember navigation controller for handling navigation between screens
            val navController = rememberNavController()

            // Remember drawer state for controlling the drawer
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

            // Coroutine scope for handling drawer operations
            val scope = rememberCoroutineScope()

            // Get the ViewModel instance from Hilt
            val viewModel: CharacterListViewModel = hiltViewModel()

            // Observe the dark theme state from ViewModel
            val darkTheme by viewModel.theme.collectAsState()

            // Apply the theme based on the darkTheme state
            ThemeSwitcherTheme(darkTheme = darkTheme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Main layout with navigation drawer and content
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

/**
 * Composable for displaying the text indicating the current theme.
 *
 * @param darkTheme Boolean indicating whether the dark theme is enabled.
 */
@Composable
fun ThemeSwitcherText(darkTheme: Boolean) {
    Text(
        text = if (darkTheme)
            stringResource(id = R.string.light_theme)
        else
            stringResource(id = R.string.dark_theme),
        modifier = Modifier.padding(16.dp)
    )
}

/**
 * Composable for the drawer content.
 *
 * @param darkTheme Boolean indicating whether the dark theme is enabled.
 * @param viewModel The ViewModel instance used to toggle the theme.
 */
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

/**
 * Composable for the main screen content including the AppBar and navigation.
 *
 * @param scope Coroutine scope for handling drawer operations.
 * @param drawerState State of the navigation drawer.
 * @param navController Controller for navigation.
 * @param viewModel The ViewModel instance for managing character data and theme.
 */
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
            // Main navigation content
            Navigation(navController, viewModel = viewModel)
        }
    }
}
