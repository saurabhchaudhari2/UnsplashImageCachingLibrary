package com.saurabh.imagecachinglibrary.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.saurabh.imagecachinglibrary.data.paging.UnsplashPagingSource
import com.saurabh.imagecachinglibrary.data.remote.UnsplashApi
import com.saurabh.imagecachinglibrary.domain.model.ImageResponse
import com.saurabh.imagecachinglibrary.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow

class ImageRepositoryImpl(
    private val unsplashApi: UnsplashApi,
    private val apiKey: String // API key injected
) : ImageRepository {

    override fun getPhotos(): Flow<PagingData<ImageResponse>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10, // Default page size, Unsplash uses 'per_page'
                enablePlaceholders = false // Optional, but good practice
            ),
            pagingSourceFactory = {
                UnsplashPagingSource(
                    api = unsplashApi,
                    authorization = apiKey // Using the injected API key
                    // initialPage can be defaulted in PagingSource if not specified here
                )
            }
        ).flow
    }
}
