package com.example.stanislav.newsapp.http

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface HeadlinesService {

    companion object {
        private const val SERVICE_ENDPOINT = "https://newsapi.org/v2/"
        private const val API_KEY = ""

        fun getService(): HeadlinesService {
            return ServiceUtils.getRetrofit(SERVICE_ENDPOINT).create(HeadlinesService::class.java)
        }
    }

    @GET("top-headlines")
    fun getHeadlines(@Query("apiKey") apiKey: String = API_KEY,
                     @Query("category") category: String = "general",
                     @Query("pageSize") pageSize: Int,
                     @Query("page") page: Int): Single<ArticlesResponse>
}

data class ArticlesResponse(val status: String, val totalResults: Int, val articles: List<Article>)

data class Article(val author: String, val title: String, val description: String)