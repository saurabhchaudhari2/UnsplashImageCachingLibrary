package com.saurabh.imagecachinglibrary.ui

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.imagecachinglibrary.ImageCachingLibrary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun NetworkImage(
    url: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    ) {
    val context = LocalContext.current
   val imageLoader = ImageCachingLibrary( context)
    // Remember a mutable state that will hold the Bitmap
    // This state will survive recompositions
    val bitmapState = remember { mutableStateOf<Bitmap?>(null) }

    // LaunchedEffect launches a coroutine that's cancelled when the composable leaves the composition
    // The key here is the URL, so if the URL changes, the coroutine will restart
    LaunchedEffect(url) {
        // Load the image in a coroutine on the IO dispatcher
        withContext(Dispatchers.IO) {
            val bitmap = imageLoader.getImage(url)
            withContext(Dispatchers.Main) {
                // Update the state with the new bitmap
                bitmapState.value = bitmap
            }
        }
    }


    // Display the image or a placeholder if the image is not loaded yet
    val bitmap = bitmapState.value
    if (bitmap != null) {
        // Display the loaded image
        Image(
            painter = BitmapPainter(bitmap.asImageBitmap()), // Using the BitmapPainter to display the bitmap
            contentDescription = null,
            modifier = modifier,
            contentScale = ContentScale.Crop
        )
    }else{
         LinearProgressIndicator(
             modifier.fillMaxWidth()
                 .height(2.dp)
                 .width(15.dp)
         )

}
}

@Composable
private fun PlaceholderContent(modifier: Modifier/*, message : String*/) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.LightGray) // Setting the background color of the Box composable to light gray
    ) {
        Text(
            "Loading...", // Displaying the text "Loading..." in the center of the Box composable
            color = Color.Gray, // Setting the color of the text to gray
            modifier = Modifier.align(Alignment.Center) // Aligning the text in the center of the Box composable
        )
    }
}