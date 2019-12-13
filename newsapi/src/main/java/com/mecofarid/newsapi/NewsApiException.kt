package com.mecofarid.newsapi

import java.lang.Exception

class NewsApiException(status: String?, code: String?, message: String?, cause: Throwable?) : Exception(message, cause)