package com.example.stanislav.newsapp.app

import android.app.Activity
import android.app.Application
import com.example.stanislav.newsapp.di.DaggerAppComponent
import com.facebook.stetho.Stetho
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class NewsApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector() = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        DaggerAppComponent.builder().application(this).build().inject(this)
    }
}