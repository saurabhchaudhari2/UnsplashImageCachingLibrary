package com.saurabh.imagecachinglibrary.presentation.viewmodel

import androidx.paging.PagingData
import app.cash.turbine.test
import com.saurabh.imagecachinglibrary.domain.model.ImageResponse
import com.saurabh.imagecachinglibrary.domain.usecase.GetImagesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

// FakeGetImagesUseCase for testing ImageListViewModel
class FakeGetImagesUseCase : GetImagesUseCase {
    var invokeCalled: Boolean = false
    var pagingDataToReturn: PagingData<ImageResponse> = PagingData.empty()

    override fun invoke(): Flow<PagingData<ImageResponse>> {
        invokeCalled = true
        return flowOf(pagingDataToReturn)
    }

    fun prepareHappyPathResponse() {
        pagingDataToReturn = PagingData.empty() // Or PagingData.from(listOf(mockImageResponse)) for more detailed tests
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
class ImageListViewModelTest {

    private lateinit var fakeGetImagesUseCase: FakeGetImagesUseCase
    private lateinit var viewModel: ImageListViewModel

    // For controlling coroutine execution in tests
    private val testScheduler = TestCoroutineScheduler()
    private val testDispatcher = StandardTestDispatcher(testScheduler)

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher) // Set the main dispatcher to our test dispatcher
        fakeGetImagesUseCase = FakeGetImagesUseCase()
        // Inject the TestDispatcher into the ViewModel's scope
        viewModel = ImageListViewModel(fakeGetImagesUseCase, testDispatcher)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain() // Reset the main dispatcher to the original one
        viewModel.clear() // Ensure the ViewModel's scope is cancelled
    }

    @Test
    fun `images flow emits PagingData from use case`() = runTest(testDispatcher) {
        fakeGetImagesUseCase.prepareHappyPathResponse()

        viewModel.images.test {
            val firstEmission = awaitItem()
            assertNotNull(firstEmission, "The first emission should not be null")
            assertTrue(fakeGetImagesUseCase.invokeCalled, "GetImagesUseCase.invoke() should have been called")

            // Here, firstEmission is PagingData. We're not testing its content deeply in this unit test,
            // but rather that the ViewModel correctly exposes the flow from the use case.
            // More detailed PagingData content tests would typically be in integration tests or
            // by using more advanced Paging testing utilities if available for KMP commonTest.

            cancelAndConsumeRemainingEvents() // Ensure the flow collector is cancelled
        }
    }
}
