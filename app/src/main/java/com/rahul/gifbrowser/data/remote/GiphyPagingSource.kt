package com.rahul.gifbrowser.data.remote

import android.util.Log
import androidx.paging.PagingSource
import com.rahul.gifbrowser.data.remote.api.GiphyApi
import com.rahul.gifbrowser.models.Data
import retrofit2.HttpException
import java.io.IOException

const val STARTING_PAGE_INDEX = 0

class GiphyPagingSource(private val api: GiphyApi) : PagingSource<Int, Data>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = api.getTrendingGif(offset = position , limit = if (position == 0) 250 else 25)
            val gifs = response.body()?.data
            Log.d("TAG", "load:>>>>> $position , ${params.loadSize}")
            LoadResult.Page(
                data = gifs!!,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 25,
                nextKey = if (gifs.isEmpty()) null else position + 25
            )

        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }

    }
}