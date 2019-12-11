package com.mecofarid.oranjnews.data.model

import com.mecofarid.newsapi.model.response.DefaultResponse


data class NewsResponse(
    val articles: List<Article>,
    val totalResults: Int
):DefaultResponse()