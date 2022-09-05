package com.example.scrollviewperformancetest.data

import com.google.gson.annotations.SerializedName

data class PicsumImageInfo(
    val author: String,
    @SerializedName("download_url")
    val downloadUrl: String,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)
