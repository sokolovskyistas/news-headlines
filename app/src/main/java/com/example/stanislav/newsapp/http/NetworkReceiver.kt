package com.example.stanislav.newsapp.http

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class NetworkReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        NetworkObservable.updateState()
    }
}

object NetworkObservable {
    private val subject = PublishSubject.create<Unit>()

    fun getNetworkState(): Observable<Unit> = subject

    fun updateState() {
        subject.onNext(Unit)
    }
}