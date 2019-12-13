## Description
This is an SDK for ![newsapi.org](https://newsapi.org/)

Note: You can get your API key at ![newsapi.org](https://newsapi.org/)

## Usage
### 1. Integration
Min API level is: `API 21` [![](https://jitpack.io/v/mecoFarid/news-api.svg)](https://jitpack.io/#mecoFarid/news-api)

**Step 1.** Add it in your root build.gradle at the end of repositories:

```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
**Step 2.** Add the dependency
```
dependencies {
    ...
    implementation 'com.github.mecoFarid:news-api:v1.0.1'
}
```
### 2. Code Sample
**Step 1.** Initialize the NewsApi with your API key 

```
NewsApiManager.setApiToken(YOUR_API_TOKEN)
```
You need to do this just once only.

**Step 2.** Call available endpoints either Synchronously or Asynchronously

```
// Instantiate NewsApi
val newsApi = NewsApi()

// Asynchronous call
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

// Synchronous call in background thread
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
```
If you want to add `from` or `to` dates to query parameters you can use this library's `Date.asNewsApiDateFormat()` function. It will convert the given `Date` to a String that's resolvable by ![newsapi.org](https://newsapi.org). 

Example: `hashMapOf("q" to "bitcoin", "from" to Date().asNewsApiDateFormat())`


NOTE: `hashMapOf("q" to "bitcoin")` is query paramter for a given endpoint. You can find available query parameters and values at ![https://newsapi.org/docs/endpoints](https://newsapi.org/docs/endpoints)
