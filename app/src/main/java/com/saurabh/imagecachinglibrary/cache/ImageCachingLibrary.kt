package com.example.imagecachinglibrary

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.collection.LruCache
import com.saurabh.imagecachinglibrary.api.UnsplashApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

class ImageCachingLibrary(private val context: Context) {
    private val inMemoryCache: LruCache<String, Bitmap>
    private val diskCacheDir: File
    private val unsplashApi: UnsplashApi

    init {
        // Initialize the in-memory cache with a maximum size of 10MB
        val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()
        val cacheSize = maxMemory / 8
        inMemoryCache = object : LruCache<String, Bitmap>(cacheSize) {
            override fun sizeOf(key: String, bitmap: Bitmap): Int {
                return bitmap.byteCount / 1024
            }
        }

        // Initialize the disk cache directory
        diskCacheDir = File(context.cacheDir, "image_cache")
        if (!diskCacheDir.exists()) {
            diskCacheDir.mkdirs()
        }

        // Initialize the Unsplash API
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.unsplash.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        unsplashApi = retrofit.create(UnsplashApi::class.java)
    }

    suspend fun getImage(imageUrl: String): Bitmap? {
        // Check the in-memory cache first
        inMemoryCache.get(imageUrl)?.let { return it }

        // Check the disk cache
        val diskCacheFile = File(diskCacheDir, imageUrl.hashCode().toString())
        if (diskCacheFile.exists()) {
            val bitmap = BitmapFactory.decodeStream(FileInputStream(diskCacheFile))
            inMemoryCache.put(imageUrl, bitmap)
            return bitmap
        }

        // If the image is not in the cache, fetch it from the Unsplash API
        return fetchImageFromApi(imageUrl)
    }

    private suspend fun fetchImageFromApi(imageUrl: String): Bitmap? {
        return withContext(Dispatchers.IO) {
            try {
                // Fetch the image from the Unsplash API
                val response = unsplashApi.getImage(imageUrl)
                val imageBytes = response.body()?.bytes()
                val bitmap = imageBytes?.let { BitmapFactory.decodeByteArray(imageBytes, 0, it.size) }

                // Cache the image on disk
                val diskCacheFile = File(diskCacheDir, imageUrl.hashCode().toString())
                FileOutputStream(diskCacheFile).use { output ->
                    bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, output)
                }

                // Cache the image in memory
                if (bitmap != null) {
                    inMemoryCache.put(imageUrl, bitmap)
                }

                bitmap
            } catch (e: IOException) {
                e.printStackTrace()
                null
            }
        }
    }
}