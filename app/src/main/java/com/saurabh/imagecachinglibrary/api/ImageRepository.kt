package com.saurabh.imagecachinglibrary.api

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.saurabh.imagecachinglibrary.paging.UnsplashPagingSource


class ImageRepository(private val api: UnsplashApi) {

    fun getPhotos(
        perPage: Int,
        authorization: String,
        pageNumber: Int
    ) = Pager(
        config = PagingConfig(pageSize = perPage),
        pagingSourceFactory = { UnsplashPagingSource(api, authorization, pageNumber) }
    ).flow
}
