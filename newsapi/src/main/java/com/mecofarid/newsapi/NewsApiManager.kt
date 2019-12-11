package com.mecofarid.newsapi

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.*

class NewsApiManager {
    companion object{
        private val mOkHttpClient = OkHttpClient.Builder().apply {
            addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
        }.build()

        private val mGson = Gson()

        private var mApiToken: String? = null

        fun setApiToken(apiToken: String){
            mApiToken = apiToken
        }

        fun getApiToken(): String? = mApiToken

        fun getOkHttpClient(): OkHttpClient = mOkHttpClient

        fun getGson(): Gson = mGson
    }
}