package com.example.stanislav.newsapp.ui

import com.example.stanislav.newsapp.model.NewsListRepository
import com.example.stanislav.newsapp.model.PageData
import com.example.stanislav.newsapp.utils.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsListPresenter
@Inject constructor(private val repository: NewsListRepository,
                    private val view: ViewI,
                    private val dataConverter: ViewDataConverter) : Presenter {

    private val modelDisposables = CompositeDisposable()
    private val viewDisposable = CompositeDisposable()

    private val data: MutableList<PageData> = mutableListOf()
    private var isLoading = false

    override fun subscribe() {
        if (data.isEmpty()) loadNews(1)
        else updateViewData()
        viewDisposable += view
                .getUserActions()
                .subscribe { action ->
                    when (action) {
                        UserAction.NextPage -> loadNews(nextPageNumber())
                    }
                }
    }

    override fun unSubscribe() {
        modelDisposables.clear()
        viewDisposable.clear()
    }

    private fun loadNews(pageIndex: Int) {
        isLoading = true
        modelDisposables.clear()
        modelDisposables += repository
                .getNews(pageIndex)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { page ->
                    addPage(pageIndex, page)
                    isLoading = false
                    updateViewData()
                }
    }

    private fun updateViewData() {
        view.setData(ViewData.Ready(dataConverter.convert(data)))
    }

    private fun nextPageNumber() = data.size + 1
    private fun addPage(index: Int, page: PageData) = data.add(index - 1, page)
}