package com.example.wizardsofhogwarts.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * A composable that displays a circular progress indicator when [isDisplayed] is true.
 *
 * @param isDisplayed Boolean flag indicating whether to show the progress indicator.
 */
@Composable
fun CircularIndeterminateProgressBar(isDisplayed: Boolean) {
    // Only display the progress indicator if isDisplayed is true
    if (isDisplayed) {
        Column(
            modifier = Modifier.fillMaxSize(), // Fill the maximum size of the parent
            verticalArrangement = Arrangement.Center, // Center vertically
            horizontalAlignment = Alignment.CenterHorizontally // Center horizontally
        ) {
            CircularProgressIndicator() // Show the progress indicator
        }
    }
}
