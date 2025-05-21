package com.saurabh.imagecachinglibrary.presentation.ui

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
import androidx.paging.compose.collectAsLazyPagingItems
import com.saurabh.imagecachinglibrary.presentation.viewmodel.ImageListViewModel
// Make sure ImageResponse is imported from the common domain model if ImageListViewModel exposes it directly,
// or if NetworkImage needs it directly. PagingItems will be of type ImageResponse from domain.model.

@Composable
fun ImageListScreen(
    viewModel: ImageListViewModel // ViewModel is now passed as a parameter
) {
    val images = viewModel.images.collectAsLazyPagingItems()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(images.itemCount) { index ->
            images[index]?.let { imageResponse ->
                NetworkImage(
                    url = imageResponse.urls.small, // Accessing from the common ImageResponse model
                    contentDescription = "Image ${imageResponse.id}", // Example content description
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(.57f)
                        .clip(RoundedCornerShape(16.dp)),
                )
            }
        }
    }
}
