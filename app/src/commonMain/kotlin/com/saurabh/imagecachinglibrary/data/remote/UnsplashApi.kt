package com.saurabh.imagecachinglibrary.data.remote

import com.saurabh.imagecachinglibrary.domain.model.ImageResponse

interface UnsplashApi {
    suspend fun getPhotos(page: Int, perPage: Int = 10, authorization: String): List<ImageResponse>
}
