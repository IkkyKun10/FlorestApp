package com.riezki.florestapp.core

import com.riezki.florestapp.core.models.Articles
import com.riezki.florestapp.core.models.News
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("everything?apiKey=d74053d1a68f4565b7e2214d9e8c6009")
    suspend fun getAllNewsEveryThing(@Query("q") query: String): Response<List<News>>

    @GET("top-headlines?apiKey=d74053d1a68f4565b7e2214d9e8c6009&country=id")
    suspend fun getAllNewsHeadline(): Response<Articles>

    companion object {
        var retrofitService: RetrofitService? = null
        val BASE_URL = "https://newsapi.org/v2/"
        val API_KEY = "d74053d1a68f4565b7e2214d9e8c6009"

        fun getInstance(): RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }

    }
}