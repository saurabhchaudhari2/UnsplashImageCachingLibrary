package com.saurabh.imagecachinglibrary.di

import com.saurabh.imagecachinglibrary.data.remote.AndroidUnsplashApiImpl
import com.saurabh.imagecachinglibrary.data.remote.RetrofitUnsplashService
import com.saurabh.imagecachinglibrary.data.remote.UnsplashApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.unsplash.com/"
private const val UNSPLASH_API_KEY_VALUE = "YOUR_ACCESS_KEY" // Placeholder

val androidModule = module {

    // OkHttpClient
    single {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .addInterceptor(logging)
            // Add other interceptors if needed (e.g., for auth key, though current UnsplashApi takes it as param)
            .build()
    }

    // Retrofit
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get<OkHttpClient>()) // Koin will inject the OkHttpClient defined above
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // RetrofitUnsplashService (Retrofit-generated interface)
    single {
        get<Retrofit>().create(RetrofitUnsplashService::class.java)
    }

    // UnsplashApi (Common interface implementation)
    single<UnsplashApi> {
        AndroidUnsplashApiImpl(get()) // Koin will inject RetrofitUnsplashService
    }

    // Provide the API Key as a String.
    // If there were multiple String dependencies, a qualifier would be needed.
    single { UNSPLASH_API_KEY_VALUE }
}
