package com.mecofarid.newsapi

import com.mecofarid.newsapi.model.response.SourcesResponse
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class NewsApiTest {

    private lateinit var mMockWebServer: MockWebServer
    private lateinit var mNewsApi: NewsApi

    @Before
    fun init(){
        mMockWebServer = MockWebServer()

        mNewsApi = NewsApi()
    }

    @After
    fun finish(){
        mMockWebServer.shutdown()
    }

    @Test
    fun utils_asNewsApiDate(){
        val date = Date()

        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val dateAsNewsApiDate = sdf.format(date)
        assert(dateAsNewsApiDate == date.asNewsApiDateFormat())
    }
}