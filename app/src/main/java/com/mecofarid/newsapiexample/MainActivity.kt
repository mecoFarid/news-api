package com.mecofarid.newsapiexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mecofarid.newsapi.NewsApi
import com.mecofarid.newsapi.NewsApiManager
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        thread {
            NewsApiManager.setApiToken("c37303be3044459c82d64a2b9dfde27d")

            val newsApi = NewsApi()
            println("meco ${newsApi.getSources().status}")
        }
    }
}
