package com.saurabh.imagecachinglibrary.domain.usecase

import androidx.paging.PagingData
import com.saurabh.imagecachinglibrary.domain.model.ImageResponse
import com.saurabh.imagecachinglibrary.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow

class GetImagesUseCaseImpl(
    private val imageRepository: ImageRepository
) : GetImagesUseCase {
    override operator fun invoke(): Flow<PagingData<ImageResponse>> {
        return imageRepository.getPhotos()
    }
}
