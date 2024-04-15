package com.saurabh.imagecachinglibrary.api

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class ImageRepository(private val api: UnsplashApi) {


    suspend fun getRandomImages(count: Int): List<String> {
        val accessToken = "SGOSpqy0RFwytK1-ECcaL5IUShlZvKpb3suL3pW3Qxc"
        return withContext(Dispatchers.IO) {
            val imageResponse = api.getRandomImages(count, accessToken)
            if (imageResponse.isNotEmpty()) {
                var list1 =  imageResponse.map { it.urls }.toString()
                var list =  imageResponse.map { it.urls.small }.toString()
                Log.d("ImageRepository", "getRandomImages: $list1")
                Log.d("ImageRepository", "getRandomImages: $list")
                // Extract the URL list from the Root object
                return@withContext imageResponse.map { it.urls.small }
            } else {
                // Handle the error
                emptyList()
            }
        }
    }
}
