package com.saurabh.imagecachinglibrary.domain.usecase

import androidx.paging.PagingData
import app.cash.turbine.test
import com.saurabh.imagecachinglibrary.domain.model.ImageResponse
import com.saurabh.imagecachinglibrary.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

// FakeImageRepository for testing GetImagesUseCaseImpl
class FakeImageRepository : ImageRepository {
    var getPhotosCalled: Boolean = false
    var pagingDataToReturn: PagingData<ImageResponse> = PagingData.empty()

    override fun getPhotos(): Flow<PagingData<ImageResponse>> {
        getPhotosCalled = true
        return flowOf(pagingDataToReturn)
    }

    fun prepareHappyPathResponse() {
        // In a real scenario, you might create PagingData with actual items.
        // For this test, PagingData.empty() is often sufficient if we're just checking the flow.
        pagingDataToReturn = PagingData.empty()
    }
}

class GetImagesUseCaseImplTest {

    private lateinit var fakeImageRepository: FakeImageRepository
    private lateinit var getImagesUseCase: GetImagesUseCaseImpl

    private fun setup() {
        fakeImageRepository = FakeImageRepository()
        getImagesUseCase = GetImagesUseCaseImpl(fakeImageRepository)
    }

    @Test
    fun `invoke calls repository getPhotos and returns its flow`() = runTest {
        setup()
        fakeImageRepository.prepareHappyPathResponse()

        val resultFlow = getImagesUseCase()

        assertNotNull(resultFlow)
        assertTrue(fakeImageRepository.getPhotosCalled, "ImageRepository.getPhotos() should have been called")

        resultFlow.test {
            val pagingData = awaitItem()
            assertNotNull(pagingData) // We expect the PagingData from the fake repository
            cancelAndConsumeRemainingEvents()
        }
    }
}
