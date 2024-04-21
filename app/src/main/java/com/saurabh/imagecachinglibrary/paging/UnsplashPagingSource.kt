package com.saurabh.imagecachinglibrary.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.saurabh.imagecachinglibrary.api.UnsplashApi
import com.saurabh.imagecachinglibrary.data.ImageResponse
import okio.IOException
import retrofit2.HttpException

class UnsplashPagingSource(
    private val api: UnsplashApi,
    private val authorization: String,
    private val initialPage: Int
) : PagingSource<Int, ImageResponse>() {
    override fun getRefreshKey(state: PagingState<Int, ImageResponse>): Int? {
        // Implement refresh logic if needed
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageResponse> {
        val page = params.key ?: initialPage
        return try {
            val response = api.getPhotos(params.loadSize, authorization, page)
            LoadResult.Page(
                data = response,
                prevKey = if (page == initialPage) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}