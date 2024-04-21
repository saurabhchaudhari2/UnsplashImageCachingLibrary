package com.saurabh.imagecachinglibrary.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.saurabh.imagecachinglibrary.api.ImageRepository
import com.saurabh.imagecachinglibrary.data.ImageResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow


import javax.inject.Inject

@HiltViewModel
class ImageListViewModel @Inject constructor(
    repository: ImageRepository
) : ViewModel() {
    private val _images = repository.getPhotos(
        perPage = 20,
        authorization = "SGOSpqy0RFwytK1-ECcaL5IUShlZvKpb3suL3pW3Qxc",
        pageNumber = 1
    ).cachedIn(viewModelScope)

    val images: Flow<PagingData<ImageResponse>> = _images
}