package com.mecofarid.newsapi.model.response

import com.mecofarid.oranjnews.data.model.Source

data class SourcesResponse(
    val sources: List<Source>
): DefaultResponse()