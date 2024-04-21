package com.saurabh.imagecachinglibrary.di

import UnsplashApiService
import com.saurabh.imagecachinglibrary.api.ImageRepository
import com.saurabh.imagecachinglibrary.api.UnsplashApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideUnsplashApi(): UnsplashApi = UnsplashApiService.api

    @Provides
    fun provideUnsplashRepository(api: UnsplashApi): ImageRepository {
        return ImageRepository(api)
    }
}