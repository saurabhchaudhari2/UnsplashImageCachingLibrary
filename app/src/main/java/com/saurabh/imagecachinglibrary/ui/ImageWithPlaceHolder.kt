package com.saurabh.imagecachinglibrary.ui

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter

@Composable
fun ImageWithPlaceholder(
    bitmap: Bitmap?, // A Bitmap object representing the image to be displayed
    modifier: Modifier = Modifier // A Modifier object to apply modifications to the composable
) = if (bitmap != null) { // Checking if the Bitmap object is not null
    Image(
        painter = rememberImagePainter(bitmap), // Creating an ImagePainter object with the Bitmap object
        contentDescription = null, // Setting the content description of the image to null
        modifier = modifier // Applying the modifier to the Image composable
    )
} else {
    PlaceholderContent(modifier) // Calling the PlaceholderContent composable if the Bitmap object is null
}

@Composable
private fun PlaceholderContent(modifier: Modifier) {
    Box(
        modifier = modifier
            .background(Color.LightGray) // Setting the background color of the Box composable to light gray
    ) {
        Text(
            "Loading...", // Displaying the text "Loading..." in the center of the Box composable
            color = Color.Gray, // Setting the color of the text to gray
            modifier = Modifier.align(Alignment.Center) // Aligning the text in the center of the Box composable
        )
    }
}

@Composable
fun rememberImagePainter(bitmap: Bitmap): Painter {
    return BitmapPainter(bitmap.asImageBitmap()) // Creating a BitmapPainter object with the ImageBitmap object created from the Bitmap object
}
