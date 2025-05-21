package com.saurabh.imagecachinglibrary.di

import com.saurabh.imagecachinglibrary.data.remote.UnsplashApi
import com.saurabh.imagecachinglibrary.data.repository.ImageRepositoryImpl
import com.saurabh.imagecachinglibrary.domain.repository.ImageRepository
import com.saurabh.imagecachinglibrary.domain.usecase.GetImagesUseCase
import com.saurabh.imagecachinglibrary.domain.usecase.GetImagesUseCaseImpl
import com.saurabh.imagecachinglibrary.presentation.viewmodel.ImageListViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val commonModule = module {
    // ViewModel
    factoryOf(::ImageListViewModel)

    // Use Cases
    factory<GetImagesUseCase> { GetImagesUseCaseImpl(get()) }

    // Repositories
    // ImageRepositoryImpl now needs UnsplashApi (from platform module) and apiKey (String, from platform module)
    factory<ImageRepository> { ImageRepositoryImpl(unsplashApi = get(), apiKey = get()) }

    // API - UnsplashApi is expected to be provided by platform-specific modules.
    // Koin will resolve this when ImageRepositoryImpl requests it.
    // The apiKey (String) is also expected to be provided by a platform-specific module.
    // When a platform module (like androidModule) provides UnsplashApi, Koin will be able to inject it.
    // factory { get<UnsplashApi>() } // This isn't strictly needed here if ImageRepositoryImpl takes it
                                     // Koin will figure out that UnsplashApi is needed by ImageRepositoryImpl
}
