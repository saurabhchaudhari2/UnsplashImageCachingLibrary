package com.saurabh.imagecachinglibrary

import RetrofitInstance
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.imagecachinglibrary.ImageCachingLibrary
import com.saurabh.imagecachinglibrary.api.ImageRepository
import com.saurabh.imagecachinglibrary.network.NetworkObserver
import com.saurabh.imagecachinglibrary.ui.CircularLoader
import com.saurabh.imagecachinglibrary.ui.ImageGridScreen

import com.saurabh.imagecachinglibrary.ui.theme.UnsplashImageCachingLibraryTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private val imageRepository = ImageRepository(RetrofitInstance.api)
private val TAG: String? = "MainActivity"
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnsplashImageCachingLibraryTheme {
                if (NetworkObserver(this@MainActivity).isConnected()) {
                    MainContent()
                } else{
                    Toast.makeText(this, "Network Unavailable. Please Check your network connection!", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    @Composable
    private fun MainContent() {
        val context = LocalContext.current // Getting the current context
        val imageUrls = remember { mutableStateListOf<String>() }
        val imageBitmaps =
            remember { mutableStateListOf<Bitmap?>() } // Remembering the list of Bitmap objects
        var isLoading by remember { mutableStateOf(true) } // Remembering the isLoading state

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            CircularLoader(
                isLoading = isLoading, // Displaying the circular loader if isLoading is true
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            )
            ImageGridScreen(
                imageBitmaps = imageBitmaps,
                modifier = Modifier.fillMaxSize()
            )
        }

        LaunchedEffect(Unit) {
            // Fetch 30 random images from the Unsplash API
            val images = imageRepository.getRandomImages(30)

            //clearing and adding all image urls in list
            imageUrls.clear()
            imageUrls.addAll(images)

            val downloadedImages =
                mutableListOf<Bitmap?>() // Creating a list to store the downloaded Bitmap objects
            coroutineScope { // Creating a coroutine scope
                launch(Dispatchers.IO) { // Launching a coroutine on the IO dispatcher
                    imageUrls.forEach { url ->
                        val bitmap = runCatching { // Using runCatching to handle any exceptions
                            val imageCachingLibrary =
                                ImageCachingLibrary(context) // Creating an instance of the ImageCachingLibrary class
                            imageCachingLibrary.getImage(url.toString()) // Downloading the image from the given URL
                        }.getOrNull()
                        downloadedImages.add(bitmap) // Adding the downloaded Bitmap object to the list
                    }
                }
            }
            withContext(Dispatchers.Main) { // Switching to the main dispatcher
                imageBitmaps.clear() // Clearing the list of Bitmap objects
                imageBitmaps.addAll(downloadedImages) // Adding the downloaded Bitmap objects to the list
                isLoading = false // Setting isLoading to false when the data is loaded
            }
        }
    }
}



