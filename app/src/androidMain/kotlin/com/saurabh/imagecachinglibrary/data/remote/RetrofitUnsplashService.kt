package com.saurabh.imagecachinglibrary.data.remote

import com.saurabh.imagecachinglibrary.domain.model.ImageResponse // Common domain model
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitUnsplashService {
    @GET("photos")
    suspend fun getPhotos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("client_id") clientId: String // The 'authorization' param from common UnsplashApi will be passed here
    ): List<ImageResponse> // Retrofit will deserialize to the common ImageResponse
}
