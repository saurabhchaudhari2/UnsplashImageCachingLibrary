package com.saurabh.imagecachinglibrary.domain.repository

import androidx.paging.PagingData
import com.saurabh.imagecachinglibrary.domain.model.ImageResponse
import kotlinx.coroutines.flow.Flow

interface ImageRepository {
    fun getPhotos(): Flow<PagingData<ImageResponse>>
}
