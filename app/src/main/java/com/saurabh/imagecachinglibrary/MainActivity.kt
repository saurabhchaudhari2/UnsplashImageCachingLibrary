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
import com.saurabh.imagecachinglibrary.network.NetworkObserver
import com.saurabh.imagecachinglibrary.ui.ImageListScreen
import com.saurabh.imagecachinglibrary.ui.theme.UnsplashImageCachingLibraryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            ImageListScreen()
        }
    }
}




