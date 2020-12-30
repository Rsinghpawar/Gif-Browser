package com.rahul.gifbrowser.data.remote.api

import com.rahul.gifbrowser.models.GiphyResult
import com.rahul.gifbrowser.utils.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyApi {

    @GET("v1/gifs/trending")
    suspend fun getTrendingGif(
        @Query(value = "api_key") apiKey: String = API_KEY ,
        @Query("limit") limit : Int = 25,
        @Query("offset") offset : Int = 0
    ): Response<GiphyResult>
}