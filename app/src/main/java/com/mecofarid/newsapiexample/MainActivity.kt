package com.mecofarid.newsapiexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mecofarid.newsapi.NewsApi
import com.mecofarid.newsapi.NewsApiManager
import com.mecofarid.newsapi.model.response.SourcesResponse
import com.mecofarid.oranjnews.data.model.NewsResponse
import java.lang.Exception
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NewsApiManager.setApiToken("c37303be3044459c82d64a2b9dfde27d")
        val newsApi = NewsApi()


        // This example uses Asynchronous calls
        newsApi.getEverythingAsync(
            hashMapOf(
                "q" to "bitcoin"
            ),
            object : NewsApi.NewsApiResponseCallback<NewsResponse> {
                override fun onSuccess(t: NewsResponse) {
                    println("meco success ${t}")
                }

                override fun onFailure(e: Exception) {
                    println("meco failure ${e}")
                }

            }
        )

        // This example uses Synchronous calls
        thread {
            newsApi.getSources(
                hashMapOf(
                    "category" to "entertainment"
                ),
                object : NewsApi.NewsApiResponseCallback<SourcesResponse> {
                    override fun onSuccess(t: SourcesResponse) {
                        println("meco success ${t}")
                    }

                    override fun onFailure(e: Exception) {
                        println("meco failure ${e}")
                    }

                }
            )
        }
    }
}
