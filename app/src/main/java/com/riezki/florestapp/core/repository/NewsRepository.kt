package com.riezki.florestapp.core.repository

import com.riezki.florestapp.core.NetworkState
import com.riezki.florestapp.core.RetrofitService
import com.riezki.florestapp.core.RetrofitService.Companion.retrofitService
import com.riezki.florestapp.core.models.Articles
import com.riezki.florestapp.core.models.News

class NewsRepository constructor(private val retrofitService: RetrofitService) {

    suspend fun getAllMovies(): NetworkState<Articles> {
        val response = retrofitService.getAllNewsHeadline()
        return if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody != null) {
                NetworkState.Success(responseBody)
            } else {
                NetworkState.Error(response)
            }
        } else {
            NetworkState.Error(response)
        }
    }
}