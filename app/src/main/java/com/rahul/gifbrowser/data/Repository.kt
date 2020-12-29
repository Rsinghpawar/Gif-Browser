package com.rahul.gifbrowser.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.rahul.gifbrowser.data.api.GiphyApi
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(private val api: GiphyApi) {

    suspend fun getTrendingGif() = api.getTrendingGif()

    fun getTrendingGifs() = Pager(
        config = PagingConfig(
            pageSize = 50,
            maxSize = 300,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { GiphyPagingSource(api) }
    ).liveData
}