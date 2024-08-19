package com.example.wizardsofhogwarts.ui

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
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.wizardsofhogwarts.ui.component.AppBarTitleAndDrawer
import com.example.wizardsofhogwarts.R
import com.example.wizardsofhogwarts.ui.characterlist.CharacterListViewModel
import com.example.wizardsofhogwarts.ui.navigation.Navigation
import com.example.wizardsofhogwarts.ui.theme.ThemeSwitcherTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val scope = rememberCoroutineScope()
            var viewModel: CharacterListViewModel = hiltViewModel()

            val darkTheme by viewModel.theme

            ThemeSwitcherTheme(darkTheme = darkTheme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    ModalNavigationDrawer(
                        drawerContent = {
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

                                            })
                                    }

                                }
                            }

                        }, drawerState = drawerState
                    ) {
                        Scaffold(
                            topBar = {
                                AppBarTitleAndDrawer(
                                    stringResource(id = R.string.app_name),
                                    onDrawerIconClick = {
                                        scope.launch {
                                            drawerState.open()
                                        }
                                    })

                            })
                        { innerPadding ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(innerPadding)
                            ) {

                                Navigation(navController, viewModel = viewModel)
                            }
                        }
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



