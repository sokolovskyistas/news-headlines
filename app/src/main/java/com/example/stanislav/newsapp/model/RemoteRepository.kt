package com.example.stanislav.newsapp.model

import android.content.Context
import android.net.ConnectivityManager
import com.example.stanislav.newsapp.http.HeadlinesService
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RemoteRepository
@Inject constructor(private val context: Context,
                    private val service: HeadlinesService) {

    companion object {
        private const val PAGE_SIZE = 20
    }

    fun getNews(page: Int): Single<PageData> =
            when (isNetworkAvailable()) {
                true -> service
                        .getHeadlines(page = page, pageSize = PAGE_SIZE)
                        .subscribeOn(Schedulers.io())
                        .map { response ->
                            when {
                                response.articles.size < PAGE_SIZE -> PageData.Page(response.articles)
                                else -> PageData.LastPage(response.articles)
                            }
                        }
                false -> Single.just(PageData.LastPage())
            }

    private fun isNetworkAvailable() = (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
            .activeNetworkInfo
            ?.isConnected ?: false
}