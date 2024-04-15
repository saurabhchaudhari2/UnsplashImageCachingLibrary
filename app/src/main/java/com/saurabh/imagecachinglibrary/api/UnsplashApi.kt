package com.saurabh.imagecachinglibrary.api


import com.saurabh.imagecachinglibrary.data.ImageResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url
import java.net.UnknownHostException


interface UnsplashApi {


    @GET("photos/random")
    suspend fun getRandomImages(
        @Query("count") count: Int,
        @Query("client_id") authorization: String
    ): List<ImageResponse>

    @GET
    suspend fun getImage(@Url imageUrl: String): Response<ResponseBody>

 /*   sealed class ImageResult {
        data class Success(val response: Response<ResponseBody>) : ImageResult()
        data class NetworkError(val exception: UnknownHostException) : ImageResult()
        data class ServerError(val code: Int) : ImageResult() // handle other error codes
    }*/

   /* sealed class RandomResult {
        data class Success(val response: List<ImageResponse>) : RandomResult()
        data class NetworkError(val exception: UnknownHostException) : RandomResult()
        data class ServerError(val code: Int) : RandomResult() // handle other error codes
    }*/
}

