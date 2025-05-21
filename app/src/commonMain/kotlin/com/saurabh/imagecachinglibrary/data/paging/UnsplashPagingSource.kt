package com.saurabh.imagecachinglibrary.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.saurabh.imagecachinglibrary.data.remote.UnsplashApi
import com.saurabh.imagecachinglibrary.domain.model.ImageResponse
// Assuming IOException and HttpException are available from a common library or expect/actual.
// For now, let's use a placeholder if they are not directly available in commonMain.
// import io.ktor.client.features.ClientRequestException // Example for Ktor if used
// import io.ktor.utils.io.errors.IOException // Example for Ktor if used

class UnsplashPagingSource(
    private val api: UnsplashApi,
    private val authorization: String, // This is the client_id for Unsplash
    private val initialPage: Int = 1
) : PagingSource<Int, ImageResponse>() {

    override fun getRefreshKey(state: PagingState<Int, ImageResponse>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page.
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageResponse> {
        val page = params.key ?: initialPage
        return try {
            val response = api.getPhotos(
                page = page,
                perPage = params.loadSize,
                authorization = authorization
            )
            LoadResult.Page(
                data = response,
                prevKey = if (page == initialPage) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: Exception) { // Using generic Exception for now. KMP might need specific exception handling.
            // For KMP, you might use platform-specific exceptions or a common exception hierarchy.
            // e.g. if (e is IOException || e is ClientRequestException)
            LoadResult.Error(e)
        }
    }
}
