package com.mecofarid.newsapiexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mecofarid.newsapi.NewsApi
import com.mecofarid.newsapi.NewsApiManager
import com.mecofarid.newsapi.model.response.SourcesResponse
import java.lang.Exception
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        thread {
            NewsApiManager.setApiToken("c37303be3044459c82d64a2b9dfde27d")

            val newsApi = NewsApi()
            newsApi.getSources(
                hashMapOf(
                    "category" to "entertainment"
                ),
                object : NewsApi.NewsApiResponseCallback<SourcesResponse> {
                    override fun onSuccess(t: SourcesResponse) {
                        println("meco err ${t}")
                    }

                    override fun onFailure(e: Exception) {
                        println("meco err ${e}")
                    }

                }
            )
        }
    }
}
