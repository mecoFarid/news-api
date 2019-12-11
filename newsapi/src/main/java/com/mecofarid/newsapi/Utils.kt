package com.mecofarid.newsapi

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

internal fun Date.toNewsApiDateFormat(): String {
    @SuppressLint("SimpleDateFormat")
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    return sdf.format(this)
}