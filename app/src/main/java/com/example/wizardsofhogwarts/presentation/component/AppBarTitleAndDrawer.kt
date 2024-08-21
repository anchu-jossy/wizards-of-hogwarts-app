package com.example.wizardsofhogwarts.presentation.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import com.example.wizardsofhogwarts.R
import kotlinx.coroutines.launch

/**
 * A top app bar with a title and a navigation drawer icon.
 *
 * @param title The title to display in the app bar.
 * @param onDrawerIconClick A callback invoked when the drawer icon is clicked.
 */
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AppBarTitleAndDrawer(
    title: String,
    onDrawerIconClick: () -> Unit
) {
    // Remember coroutine scope for launching coroutines
    val scope = rememberCoroutineScope()

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        title = {
            Text(text = stringResource(id = R.string.app_name)) // Display app name as title
        },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    onDrawerIconClick() // Invoke callback on drawer icon click
                }
            }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu" // Description for accessibility
                )
            }
        }
    )
}
