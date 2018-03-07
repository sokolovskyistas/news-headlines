package com.example.stanislav.newsapp.ui

import com.example.stanislav.newsapp.model.PageData
import com.example.stanislav.newsapp.ui.epoxy.ArticleModel
import io.reactivex.Observable
import io.reactivex.Single

interface Presenter {
    fun subscribe()
    fun unSubscribe()
}

interface Repository {
    fun getNews(page: Int): Single<PageData>
}

interface ViewI {
    fun setData(data: ViewData)
    fun getUserActions(): Observable<UserAction>
}

sealed class UserAction {
    object NextPage : UserAction()
}

sealed class ViewData {
    object Empty : ViewData()
    class Ready(val items: List<ArticleModel>) : ViewData()
}