package com.saurabh.imagecachinglibrary

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.saurabh.imagecachinglibrary.presentation.theme.UnsplashImageCachingLibraryTheme
import com.saurabh.imagecachinglibrary.presentation.ui.ImageListScreen
import com.saurabh.imagecachinglibrary.presentation.viewmodel.ImageListViewModel
import org.koin.android.ext.android.inject


class MainActivity : ComponentActivity() {

    private val imageListViewModel: ImageListViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnsplashImageCachingLibraryTheme {
             //   if (NetworkObserver(this@MainActivity).isConnected()) {
                    MainContent()
               /* } else{
                    Toast.makeText(this, "Network Unavailable. Please Check your network connection!", Toast.LENGTH_SHORT).show()
                }*/
            }

        }
    }

    @Composable
    private fun MainContent() {
        // The NetworkObserver part is commented out, retaining that behavior.
        // if (NetworkObserver(this@MainActivity).isConnected()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            ImageListScreen(viewModel = imageListViewModel)
        }
        // } else {
        //     Toast.makeText(this, "Network Unavailable. Please Check your network connection!", Toast.LENGTH_SHORT).show()
        // }
    }
}




