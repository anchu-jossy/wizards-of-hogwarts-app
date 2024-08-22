package com.example.wizardsofhogwarts.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.wizardsofhogwarts.R

/**
 * A composable that displays a placeholder message when no data is available.
 *
 * The message is centered both vertically and horizontally on the screen.
 */
@Composable
fun NoDataPlaceHolder() {
    // Container for the no data placeholder message
    Column(
        modifier = Modifier.fillMaxSize().testTag("no_data_placeholder"), // Makes the column fill the entire screen, // Fills the maximum size of the parent container
        verticalArrangement = Arrangement.Center, // Centers content vertically
        horizontalAlignment = Alignment.CenterHorizontally // Centers content horizontally
    ) {
        // No data placeholder text
        Text(
            text = stringResource(id = R.string.no_data_placeholder), // Retrieves the string resource for the placeholder message
            modifier = Modifier.fillMaxWidth(), // Makes the Text composable fill the width of its parent
            textAlign = TextAlign.Center, // Centers text horizontally within its bounds
            style = MaterialTheme.typography.titleMedium // Applies the medium title typography style from the MaterialTheme
        )
    }
}
