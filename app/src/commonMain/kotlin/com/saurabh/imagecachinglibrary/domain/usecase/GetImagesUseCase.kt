package com.saurabh.imagecachinglibrary.domain.usecase

import androidx.paging.PagingData
import com.saurabh.imagecachinglibrary.domain.model.ImageResponse
import kotlinx.coroutines.flow.Flow

interface GetImagesUseCase {
    operator fun invoke(): Flow<PagingData<ImageResponse>>
}
