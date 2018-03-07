/*
package com.example.stanislav.newsapp.ui

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.example.stanislav.newsapp.http.HeadlinesService
import com.example.stanislav.newsapp.http.NetworkStateObservable
import com.example.stanislav.newsapp.utils.plusAssign
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NewsViewModel(app: Application) : AndroidViewModel(app) {

    private val data = MutableLiveData<ViewData>()
    private val disposables = CompositeDisposable()
    private val dataConverter = DataConverter()
    private val networkState = NetworkStateObservable(getApplication())

    init {
        disposables += networkState
                .hasConnection()
                .switchMap { hasConnection ->
                    when (hasConnection) {
                        false -> Observable.just(ViewData.Empty)
                        true -> HeadlinesService
                                .getService()
                                .getHeadlines()
                                .subscribeOn(Schedulers.io())
                                .map { ViewData.Ready(dataConverter.convert(it.articles)) }
                    }
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    data.value = it
                }
    }

    fun getData(): MutableLiveData<ViewData> = data

    override fun onCleared() {
        disposables.clear()
    }
}*/
