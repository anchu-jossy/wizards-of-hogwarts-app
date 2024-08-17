package com.example.wizardsofhogwarts.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.example.mcoeexercise.component.AppBarTitle
import com.example.wizardsofhogwarts.R
import com.example.wizardsofhogwarts.ui.component.AppBarWithArrow
import com.example.wizardsofhogwarts.ui.navigation.Navigation
import com.example.wizardsofhogwarts.ui.navigation.Screen
import com.example.wizardsofhogwarts.ui.navigation.currentRoute
import com.example.wizardsofhogwarts.ui.navigation.navigationTitle
import com.example.wizardsofhogwarts.ui.theme.ThemeSwitcherTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var darkTheme by remember { mutableStateOf(false) }
            val navController = rememberNavController()

            ThemeSwitcherTheme(darkTheme = darkTheme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        topBar = {
                            when (currentRoute(navController = navController)) {
                                Screen.CharactersListScreen.route -> {
                                    AppBarTitle(stringResource(R.string.characters))
                                }
                                else -> {
                                    AppBarWithArrow(navController, navigationTitle(navController)) {
                                        navController.popBackStack()
                                    }
                                }

                            }
                        }
                    ) { innerPadding ->
                        Box(
                            modifier = Modifier.fillMaxWidth().padding(innerPadding)
                        ) {
                            Navigation(navController)
                        }
                    }
                }
            }
        }
    }
}
