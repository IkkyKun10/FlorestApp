package com.riezki.florestapp.model

import android.graphics.drawable.Drawable

data class CommentModel(
    val photoProfile: Drawable,
    val username: String,
    val comment: String
)
