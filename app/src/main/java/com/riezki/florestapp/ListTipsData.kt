package com.riezki.florestapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListTipsData(
    var judul: String,
    var deskripsi: String
): Parcelable
