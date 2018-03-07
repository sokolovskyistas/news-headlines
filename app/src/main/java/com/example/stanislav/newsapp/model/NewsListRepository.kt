package com.example.stanislav.newsapp.model

import com.example.stanislav.newsapp.http.Article
import com.example.stanislav.newsapp.ui.Repository
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsListRepository
@Inject constructor(private val remoteRepository: RemoteRepository) : Repository {

    private val cachedData = CachedPagesData()

    override fun getNews(page: Int): Single<PageData> {
        return when {
            cachedData.hasCachedPage(page) -> Single.just(cachedData.getCachedPage(page))
            else -> loadPage(page)
        }
    }

    private fun loadPage(page: Int): Single<PageData> {
        return remoteRepository
                .getNews(page)
                .doOnSuccess { cachedData.cachePage(page, it) }
    }
}

private class CachedPagesData {
    private val cachedData: MutableList<PageData> = mutableListOf()

    fun getCachedPage(index: Int) = cachedData[index - 1]
    fun hasCachedPage(index: Int) = index <= cachedData.size
    fun cachePage(index: Int, pageData: PageData) = cachedData.add(index - 1, pageData)
}

sealed class PageData(val data: List<Article>) {
    data class Page(private val items: List<Article>) : PageData(items)
    data class LastPage(private val items: List<Article> = emptyList()) : PageData(items)
}