package com.example.stanislav.newsapp.http

import android.content.Context
import android.net.ConnectivityManager
import io.reactivex.Observable

class NetworkStateObservable(private val context: Context) {

    fun hasConnection(): Observable<Boolean> {
        return when (isNetworkAvailable()) {
            false -> {
                NetworkObservable
                        .getNetworkState()
                        .map { isNetworkAvailable() }
                        .filter { it }
                        .startWith(false)
                        .distinctUntilChanged()
            }
            true -> Observable.just(true)
        }
    }

    private fun isNetworkAvailable() = (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
            .activeNetworkInfo
            ?.isConnected ?: false
}