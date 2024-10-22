package com.example.wizardsofhogwarts.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/**
 * A composable that displays an error message centered on the screen.
 *
 * @param text The error message to display.
 * @param color The color of the text.
 */
@Composable
fun ErrorView(text: String, color: Color) {
    // Container for the error message
    Column(
        modifier = Modifier.fillMaxSize(), // Fills the maximum size of the parent container
        verticalArrangement = Arrangement.Center, // Centers content vertically
        horizontalAlignment = Alignment.CenterHorizontally // Centers content horizontally
    ) {
        // Error message text
        Text(
            text = text, // The error message to display
            color = color, // The color of the text
            textAlign = TextAlign.Center, // Centers text horizontally within its bounds
            modifier = Modifier
                .fillMaxWidth() // Makes the Text composable fill the width of its parent
                .padding(horizontal = 20.dp) // Adds horizontal padding
        )
    }
}
