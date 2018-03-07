package com.example.stanislav.newsapp.utils

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

operator fun CompositeDisposable.plusAssign(subscribe: Disposable) {
    this.add(subscribe)
}