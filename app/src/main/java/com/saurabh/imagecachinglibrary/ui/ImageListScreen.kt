package com.saurabh.imagecachinglibrary.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems


@Composable
fun ImageListScreen(
    viewModel: ImageListViewModel = hiltViewModel()
) {
    val images = viewModel.images.collectAsLazyPagingItems()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // Setting the number of columns in the grid to 2
        contentPadding = PaddingValues(16.dp), // Setting the padding around the grid
        verticalArrangement = Arrangement.spacedBy(16.dp), // Setting the vertical spacing between the items in the grid
        horizontalArrangement = Arrangement.spacedBy(16.dp), // Setting the horizontal spacing between the items in the grid

        //modifier = modifier
    ) {
        items(images.itemCount) { index ->
            NetworkImage(
                url = images[index]?.urls?.small ?: "",
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth() // Setting the width of the ImageWithPlaceholder composable to fill the available width
                    .aspectRatio(.57f) // Setting the aspect ratio of the ImageWithPlaceholder composable to 1:1
                    .clip(RoundedCornerShape(16.dp)), // Rounding the corners of the ImageWithPlaceholder composable
            )
        }
    }
}