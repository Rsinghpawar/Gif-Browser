package com.rahul.gifbrowser.viewmodels

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.rahul.gifbrowser.data.remote.Repository


class MainViewModel @ViewModelInject constructor(
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {

    val gifsPagingRes = repository.getTrendingGifs().cachedIn(viewModelScope)

}

