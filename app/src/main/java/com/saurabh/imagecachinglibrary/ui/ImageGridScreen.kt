@file:OptIn(ExperimentalMaterial3Api::class)

package com.saurabh.imagecachinglibrary.ui

import android.graphics.Bitmap
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

// A composable function that displays a grid of images with a snackbar at the bottom
@Composable
fun ImageGridScreen(
    imageBitmaps: List<Bitmap?>, // A list of Bitmap objects representing the images to be displayed
    modifier: Modifier
) {
    // Creating a SnackbarHostState object to manage the snackbar
    val snackbarHostState = remember { SnackbarHostState() }

    // A Box composable that contains the image grid and the snackbar
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background) // Setting the background color of the Box composable
    ) {
        // Calling the TopAppBar composable to display the top bar
        // Calling the TopAppBar composable to display the top bar
        TopAppBar(
            title = { Text("Image Grid") }, // Setting the title of the top bar
            //backgroundColor = Color.White, // Setting the background color of the TopAppBar composable
            modifier = Modifier.fillMaxWidth() // Setting the width of the TopAppBar composable to fill the available width
        )
        // Calling the ImageGrid composable to display the image grid
        ImageGrid(
            imageBitmaps = imageBitmaps,
            modifier = Modifier.fillMaxSize()
                .padding(top = 56.dp)
        )

        // Calling the SnackbarHost composable to display the snackbar
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.fillMaxSize()
        )
    }
}

// A composable function that displays a grid of images
@Composable
fun ImageGrid(
    imageBitmaps: List<Bitmap?>, // A list of Bitmap objects representing the images to be displayed
    modifier: Modifier = Modifier // A Modifier object to apply modifications to the composable
) {
    // A LazyVerticalGrid composable that displays the image grid
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // Setting the number of columns in the grid to 2
        contentPadding = PaddingValues(16.dp), // Setting the padding around the grid
        verticalArrangement = Arrangement.spacedBy(16.dp), // Setting the vertical spacing between the items in the grid
        horizontalArrangement = Arrangement.spacedBy(16.dp), // Setting the horizontal spacing between the items in the grid
        modifier = modifier
    ) {
        // A loop that creates an ImageWithPlaceholder composable for each Bitmap object in the imageBitmaps list
        items(imageBitmaps.size) { index ->
            ImageWithPlaceholder(
                bitmap = imageBitmaps[index], // The Bitmap object to be displayed
                modifier = Modifier
                    .fillMaxWidth() // Setting the width of the ImageWithPlaceholder composable to fill the available width
                    .aspectRatio(1f) // Setting the aspect ratio of the ImageWithPlaceholder composable to 1:1
                    .clip(RoundedCornerShape(8.dp)) // Rounding the corners of the ImageWithPlaceholder composable
            )
        }
    }
}

// A composable function that displays a circular loader
@Composable
fun CircularLoader(
    isLoading: Boolean, // A boolean value that indicates whether the loader is visible or not
    modifier: Modifier = Modifier // A Modifier object to apply modifications to the composable
) {
    if (isLoading) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(50.dp)
            )
        }
    }
}
