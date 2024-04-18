package com.saurabh.imagecachinglibrary.api


import com.saurabh.imagecachinglibrary.data.ImageResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url


interface UnsplashApi {


    @GET("photos/random")
    suspend fun getRandomImages(
        @Query("count") count: Int,
        @Query("client_id") authorization: String
    ): List<ImageResponse>

    @GET("photos")
    suspend fun getPhotos(
        @Query("per_page") perPage:Int,
        @Query("client_id") authorization: String,
        @Query("page") page:Int
    ): List<ImageResponse>

    @GET
    suspend fun getImage(@Url imageUrl: String): Response<ResponseBody>

}

