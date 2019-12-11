package com.mecofarid.newsapi

import com.mecofarid.newsapi.model.response.DefaultResponse
import com.mecofarid.newsapi.model.response.SourcesResponse
import com.mecofarid.oranjnews.data.model.NewsResponse
import okhttp3.HttpUrl
import okhttp3.Request
import okhttp3.Response
import java.lang.IllegalArgumentException


private const val SCHEME_NEWS_API = "https"
private const val HOST_NEWS_API = "newsapi.org"
private const val VERSION_2_NEWS_API = "v2"
private const val TOP_HEADLINES_ENDPOINT = "top-headlines"
private const val EVERYTHING_ENDPOINT = "everything"
private const val SOURCES_ENDPOINT = "sources"
private const val API_KEY_HEADER = "X-Api-Key"

private const val EMPTY_OR_NULL_RESPONSE_BODY = "Server returns null or empty response"

class NewsApi{
    private val mRequestBuilder: Request.Builder
    private var mHttpUrlBuilder: HttpUrl.Builder

    init {
        with(NewsApiManager.getApiToken()) {

            validateResolvableApiToken(apiToken = this)

            /**
             * Token can't be null here. [validateResolvableApiToken] will check for `non-null` api token and will throw exception
             * if token is `null`
             */
            mRequestBuilder = Request.Builder()
                .addHeader(API_KEY_HEADER, this!!)
        }

        mHttpUrlBuilder = HttpUrl.Builder()
            .scheme(SCHEME_NEWS_API)
            .host(HOST_NEWS_API)
            .addPathSegment(VERSION_2_NEWS_API)
    }

    fun getSources(): SourcesResponse{
        with(mHttpUrlBuilder.addPathSegment(SOURCES_ENDPOINT).build()) {
            return execute(
                request = mRequestBuilder.url(this).build(),
                classOfReturnType = SourcesResponse::class.java
            )
        }
    }

    fun getEverything(queryParams: Map<String, Any?>): NewsResponse{
        for ((key, value) in queryParams){
            mHttpUrlBuilder.addQueryParameter(key, value.toString())
        }

        with(mHttpUrlBuilder.addPathSegment(EVERYTHING_ENDPOINT).build()){
            return execute(
                request = mRequestBuilder.url(this).build(),
                classOfReturnType = NewsResponse::class.java
            )
        }
    }

    fun getHeadlines(queryParams: Map<String, Any>): NewsResponse{
        for ((key, value) in queryParams){
            mHttpUrlBuilder.addQueryParameter(key, value.toString())
        }

        with(mHttpUrlBuilder.addPathSegment(TOP_HEADLINES_ENDPOINT).build()){
            return execute(
                request = mRequestBuilder.url(this).build(),
                classOfReturnType = NewsResponse::class.java
            )
        }
    }

    private fun <T> execute(request: Request, classOfReturnType: Class<T>): T{
        with(NewsApiManager.getOkHttpClient().newCall(request).execute()) {
            return processResponse(
                response = this,
                classOfReturnType = classOfReturnType
            )
        }
    }

    private fun <T> processResponse(response: Response, classOfReturnType: Class<T>): T {
        with(response.body?.string()) {
            if (this.isNullOrBlank())
                @Suppress("UNCHECKED_CAST")
                return  DefaultResponse(message = EMPTY_OR_NULL_RESPONSE_BODY) as T


            return NewsApiManager.getGson().fromJson(
                this,
                classOfReturnType
            )
        }
    }

    private fun validateResolvableApiToken(apiToken: String?){
        // Throw exception if Api Token hasn't been set before calling any endpoints
        if (apiToken == null) {
            throw IllegalArgumentException("Api Token can't be null - did you forget to call 'NewsApiManager.setApiToken()'?")
        }
    }
}