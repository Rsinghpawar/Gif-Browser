package com.rahul.gifbrowser.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.rahul.gifbrowser.data.Repository
import com.rahul.gifbrowser.models.GiphyResult
import com.rahul.gifbrowser.utils.NetworkResult
import kotlinx.coroutines.launch
import retrofit2.Response


class MainViewModel @ViewModelInject constructor(
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {

    val gifResponse: MutableLiveData<NetworkResult<GiphyResult>> = MutableLiveData()
    val gifsPagingRes = repository.getTrendingGifs().cachedIn(viewModelScope)

    init {
       // getGif()
    }

    private fun getGif() = viewModelScope.launch {
        getGifSafeCall()
    }

    private suspend fun getGifSafeCall() {
        gifResponse.value = NetworkResult.Loading()
        if (hasInternetConnection()) {
            try {
                val response = repository.getTrendingGif()
                gifResponse.value = handleGifResponse(response)
            } catch (e: Exception) {

            }
        }
    }

    private fun handleGifResponse(response: Response<GiphyResult>): NetworkResult<GiphyResult>? {
        return when {
            response.body()!!.data.isNullOrEmpty() -> NetworkResult.Error(message = "Recipes not found.")
            response.isSuccessful -> {
                val gifs = response.body()
                NetworkResult.Success(data = gifs!!)
            }
            else -> NetworkResult.Error("Something Went Wrong")
        }
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<Application>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }


    }

}

