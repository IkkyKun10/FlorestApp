package com.riezki.florestapp.core.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductItem(
    val idProduct: String? = null,
    val titleProduct: String? = null,
    val desc: String? = null,
    val imgProduct: String? = null
) : Parcelable
