package com.riezki.florestapp.core.models

import com.google.gson.annotations.SerializedName

data class Articles(
    val articles: List<News>
)

data class News(
    val title: String,
    val author: String,
    @SerializedName("description")
    val desc: String,
    val url: String,
    val content: String,
    @SerializedName("publishedAt")
    val publishDate: String,
    @SerializedName("urlToImage")
    val imgUrl: String,
    //val source: Author
)

data class Author(
    val id: Int?,
    val name: String
)