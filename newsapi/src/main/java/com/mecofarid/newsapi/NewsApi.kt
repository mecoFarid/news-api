package com.mecofarid.newsapi

import com.mecofarid.newsapi.model.response.SourcesResponse
import com.mecofarid.oranjnews.data.model.NewsResponse
import okhttp3.HttpUrl
import okhttp3.Request
import okhttp3.Response
import java.lang.Exception
import java.lang.IllegalArgumentException


private const val SCHEME_NEWS_API = "https"
private const val HOST_NEWS_API = "newsapi.org"
private const val VERSION_2_NEWS_API = "v2"
private const val TOP_HEADLINES_ENDPOINT = "top-headlines"
private const val EVERYTHING_ENDPOINT = "everything"
private const val SOURCES_ENDPOINT = "sources"
private const val API_KEY_HEADER = "X-Api-Key"

class NewsApi{

    fun getSources(queryParams: Map<String, Any?>, callback: NewsApiResponseCallback<SourcesResponse>){
        execute(
            request = buildHttpRequest(
                path = SOURCES_ENDPOINT,
                apiToken = NewsApiManager.getApiToken(),
                queryParams = queryParams
            ),
            callback = callback
        )
    }

    fun getEverything(queryParams: Map<String, Any?>, callback: NewsApiResponseCallback<NewsResponse>){
        execute(
            request = buildHttpRequest(
                path = EVERYTHING_ENDPOINT,
                apiToken = NewsApiManager.getApiToken(),
                queryParams = queryParams
            ),
            callback = callback
        )
    }

    fun getHeadlines(queryParams: Map<String, Any>, callback: NewsApiResponseCallback<NewsResponse>){
        execute(
            request = buildHttpRequest(
                path = TOP_HEADLINES_ENDPOINT,
                apiToken = NewsApiManager.getApiToken(),
                queryParams = queryParams
            ),
            callback = callback
        )
    }

    private inline fun <reified T> execute(request: Request, callback: NewsApiResponseCallback<T>) {
        try {
            with(NewsApiManager.getOkHttpClient().newCall(request).execute()) {
                callback.onSuccess(
                    deserializeResponse(
                        response = this,
                        responseDeserializationType = T::class.java
                    )
                )
            }
        } catch (e: Exception) {
            callback.onFailure(e)
        }

    }

    private fun <T> deserializeResponse(response: Response, responseDeserializationType: Class<T>): T {
        return NewsApiManager.getGson().fromJson(
            response.body?.string(),
            responseDeserializationType
        )
    }

    private fun buildHttpRequest(path: String, apiToken: String?, queryParams: Map<String, Any?>): Request{

        validateResolvableApiToken(apiToken)

        /**
         * Token can't be null here. [validateResolvableApiToken] will check for `non-null` api token and will throw exception
         * if token is `null`
         */
        return Request.Builder().apply {
            addHeader(API_KEY_HEADER, apiToken!!)
            url(
                buildHttpUrl(
                    path,
                    queryParams
                )
            )
        }.build()
    }

    private fun buildHttpUrl(path: String, queryParams: Map<String, Any?>): HttpUrl{

        return HttpUrl.Builder().apply {
            scheme(SCHEME_NEWS_API)
            host(HOST_NEWS_API)
            addPathSegment(VERSION_2_NEWS_API)
            addPathSegment(path)

            for ((key, value) in queryParams){
                addQueryParameter(key, value.toString())
            }
        }.build()
    }

    private fun validateResolvableApiToken(apiToken: String?){
        // Throw exception if Api Token hasn't been set before calling any endpoints
        if (apiToken == null) {
            throw IllegalArgumentException("Api Token can't be null - did you forget to call 'NewsApiManager.setApiToken()'?")
        }
    }

    interface NewsApiResponseCallback<T>{
        fun onSuccess(t: T)

        fun onFailure(e: Exception)
    }
}