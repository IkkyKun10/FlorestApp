package com.riezki.florestapp.core.entity

import android.graphics.drawable.Drawable
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TipsContentEntity(
    val idTips: String? = null,
    val titleTips: String? = null,
    val descTips: String? = null,
    val imgTips: String? = null,
) : Parcelable

data class CommentItem(
    val photoProfile: Drawable,
    val username: String,
    val comment: String
)
