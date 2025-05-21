package com.saurabh.imagecachinglibrary.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale

@Composable
actual fun ExpectedCoilImage(
    url: String,
    contentDescription: String?,
    modifier: Modifier,
    contentScale: ContentScale // Parameter is present, though not directly used by this placeholder
) {
    Box(
        modifier = modifier.fillMaxSize().background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        Text("iOS Placeholder for: $url", color = Color.Gray)
    }
}
