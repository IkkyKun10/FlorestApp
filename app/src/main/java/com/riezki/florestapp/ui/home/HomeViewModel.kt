package com.riezki.florestapp.ui.home

import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riezki.florestapp.core.NetworkState
import com.riezki.florestapp.core.models.Articles
import com.riezki.florestapp.core.models.News
import com.riezki.florestapp.core.repository.NewsRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel constructor(private val newsRepository: NewsRepository) : ViewModel() {

    val newsList = MutableLiveData<Articles>()
    val loading = MutableLiveData<Boolean>()
    val selectedItems = MutableLiveData<News>();

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun getTopHeadlinesNews() {
        viewModelScope.launch {
            when (val response = newsRepository.getAllMovies()) {
                is NetworkState.Success -> {
                    loading.postValue(false)
                    newsList.postValue(response.data)
                }
                is NetworkState.Error -> {
                    loading.postValue(false)
                }
            }
        }
    }

    fun setSelectedItems(item: News){
        selectedItems.postValue(item)
    }

    private fun onError(message: String) {
        _errorMessage.value = message
        loading.value = false
    }

}