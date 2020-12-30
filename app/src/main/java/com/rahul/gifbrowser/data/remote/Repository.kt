package com.rahul.gifbrowser.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.rahul.gifbrowser.data.remote.api.GiphyApi
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(private val api: GiphyApi) {


    fun getTrendingGifs() = Pager(
        config = PagingConfig(
            pageSize = 50,
            maxSize = 300,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { GiphyPagingSource(api) }
    ).liveData
}