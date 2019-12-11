package com.mecofarid.oranjnews.data.model

import java.io.Serializable

data class Source(
    val id: String,
    val name: String,
    val description: String,
    val url: String,
    val category: String,
    val language: String,
    val country: String
)