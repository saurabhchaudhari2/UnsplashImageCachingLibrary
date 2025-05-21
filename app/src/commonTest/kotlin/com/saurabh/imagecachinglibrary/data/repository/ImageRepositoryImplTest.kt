package com.saurabh.imagecachinglibrary.data.repository

import app.cash.turbine.test
import com.saurabh.imagecachinglibrary.data.remote.UnsplashApi
import com.saurabh.imagecachinglibrary.domain.model.ImageResponse
import com.saurabh.imagecachinglibrary.domain.model.ImageUrls
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertNotNull

// FakeUnsplashApi for testing ImageRepositoryImpl
class FakeUnsplashApi : UnsplashApi {
    var mockImageResponses: List<ImageResponse> = emptyList()
    var shouldThrowError: Boolean = false
    var pageRequested: Int = -1
    var perPageRequested: Int = -1
    var authorizationRequested: String? = null

    override suspend fun getPhotos(page: Int, perPage: Int, authorization: String): List<ImageResponse> {
        if (shouldThrowError) {
            throw Exception("Fake API error")
        }
        pageRequested = page
        perPageRequested = perPage
        authorizationRequested = authorization
        return mockImageResponses
    }

    fun prepareHappyPathResponse() {
        mockImageResponses = listOf(
            ImageResponse("1", ImageUrls("reg1", "small1")),
            ImageResponse("2", ImageUrls("reg2", "small2"))
        )
        shouldThrowError = false
    }
}

class ImageRepositoryImplTest {

    private lateinit var fakeUnsplashApi: FakeUnsplashApi
    private lateinit var imageRepository: ImageRepositoryImpl
    private val testApiKey = "test_api_key"

    // Using a common setup function for KMP tests if @BeforeEach is not available directly in commonTest
    // For now, manual setup in each test or direct initialization.
    // @BeforeEach // if using a KMP test runner that supports it.
    private fun setup() {
        fakeUnsplashApi = FakeUnsplashApi()
        imageRepository = ImageRepositoryImpl(fakeUnsplashApi, testApiKey)
    }

    @Test
    fun `getPhotos returns non-null flow of PagingData`() = runTest {
        setup() // Manual setup
        fakeUnsplashApi.prepareHappyPathResponse()

        val photosFlow = imageRepository.getPhotos()
        assertNotNull(photosFlow)

        // Optionally, try to collect the first item to ensure the PagingSource is created
        // This is a basic check. For more robust PagingData testing, libraries like app.cash.turbine
        // can be used, along with more complex Pager setup for tests.
        // The actual PagingData content verification is more involved.
        photosFlow.test {
            val pagingData = awaitItem()
            assertNotNull(pagingData)
            // Further checks would involve creating a Pager, collecting from it, and asserting content.
            // For this test, ensuring PagingData is emitted is the primary goal.
            cancelAndConsumeRemainingEvents()
        }
    }
}
