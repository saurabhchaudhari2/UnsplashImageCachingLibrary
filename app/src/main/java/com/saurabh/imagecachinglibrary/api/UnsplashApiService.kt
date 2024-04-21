import com.saurabh.imagecachinglibrary.api.UnsplashApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * This object handles the creation of the Retrofit instance with OkHttp logging enabled.
 */
object UnsplashApiService {

    private const val BASE_URL = "https://api.unsplash.com/"

    private val okHttpClient by lazy {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY // Set log level (BODY for full content)

        OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient) // Use the OkHttp client with logging
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: UnsplashApi by lazy {
        retrofit.create(UnsplashApi::class.java)
    }
}
