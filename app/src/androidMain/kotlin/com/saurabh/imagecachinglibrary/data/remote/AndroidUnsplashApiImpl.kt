package com.saurabh.imagecachinglibrary.data.remote

import com.saurabh.imagecachinglibrary.domain.model.ImageResponse

class AndroidUnsplashApiImpl(
    private val retrofitService: RetrofitUnsplashService
) : UnsplashApi { // Implements the common UnsplashApi interface

    override suspend fun getPhotos(page: Int, perPage: Int, authorization: String): List<ImageResponse> {
        // The 'authorization' from the common interface is used as 'clientId' for the Retrofit service
        return retrofitService.getPhotos(page = page, perPage = perPage, clientId = authorization)
    }
}
