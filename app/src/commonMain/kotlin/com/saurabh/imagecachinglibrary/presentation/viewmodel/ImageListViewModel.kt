package com.saurabh.imagecachinglibrary.presentation.viewmodel

import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.saurabh.imagecachinglibrary.domain.model.ImageResponse
import com.saurabh.imagecachinglibrary.domain.usecase.GetImagesUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext

// A simple CoroutineScope for the ViewModel. In a real KMP app, you might use a dedicated
// KMP ViewModel library or a more structured approach for managing scope lifecycle.
// CoroutineContext is injected for testability.
class ImageListViewModel(
    getImagesUseCase: GetImagesUseCase,
    defaultContext: CoroutineContext = Dispatchers.Default + SupervisorJob()
) {
    // Create a CoroutineScope using the provided context
    private val viewModelScope = CoroutineScope(defaultContext)

    val images: Flow<PagingData<ImageResponse>> = getImagesUseCase()
        .cachedIn(viewModelScope)

    // Call this when the ViewModel is no longer needed to cancel the scope
    fun clear() {
        viewModelScope.cancel() // Cancels the scope and its jobs
    }
}
