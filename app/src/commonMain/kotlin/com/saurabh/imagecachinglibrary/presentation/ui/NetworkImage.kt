package com.saurabh.imagecachinglibrary.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale

// KMP_TARGET: This file now uses the expect/actual mechanism for platform-specific image loading.
// The actual implementations are in:
// - androidMain: .../presentation/ui/ActualImage.kt (using Coil)
// - iosMain: .../presentation/ui/ActualImage.kt (using a placeholder)

@Composable
fun NetworkImage(
    url: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop // Added to match ExpectedCoilImage
) {
    ExpectedCoilImage(
        url = url,
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale
    )
}
