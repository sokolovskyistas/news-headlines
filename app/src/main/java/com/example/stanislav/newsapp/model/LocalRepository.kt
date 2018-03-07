package com.example.stanislav.newsapp.model

import com.example.stanislav.newsapp.http.Article
import io.reactivex.Flowable
import javax.inject.Inject

class LocalRepository @Inject constructor() {
    fun getNews(size: Int): Flowable<List<Article>> {
        return Flowable.just(emptyList())
    }
}