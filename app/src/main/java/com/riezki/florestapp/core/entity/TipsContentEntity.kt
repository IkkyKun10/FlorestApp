package com.riezki.florestapp.core.entity

import android.graphics.drawable.Drawable

data class TipsContentEntity(
    val idTips: String? = null,
    val titleTips: String? = null,
    val descTips: String? = null,
    val imgTips: String? = null,
    val listCommentItem: List<CommentItem>? = null,
)

data class CommentItem(
    val photoProfile: Drawable,
    val username: String,
    val comment: String
)
