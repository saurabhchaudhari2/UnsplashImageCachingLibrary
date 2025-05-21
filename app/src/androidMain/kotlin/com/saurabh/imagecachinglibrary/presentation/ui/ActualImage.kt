package com.saurabh.imagecachinglibrary.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

@Composable
actual fun ExpectedCoilImage(
    url: String,
    contentDescription: String?,
    modifier: Modifier,
    contentScale: ContentScale
) {
    AsyncImage(
        model = url,
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale,
        // You can add placeholders, error handlers, etc. here if needed
        // placeholder = painterResource(R.drawable.placeholder), // Example
        // error = painterResource(R.drawable.error_image), // Example
    )
}
