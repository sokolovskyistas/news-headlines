package com.example.stanislav.newsapp.di

import android.app.Activity
import com.example.stanislav.newsapp.ui.NewsListActivity
import com.example.stanislav.newsapp.ui.NewsListComponent
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class ActivityBindingModule {
    @Binds
    @IntoMap
    @ActivityKey(NewsListActivity::class)
    abstract fun bindNewsListActivity(builder: NewsListComponent.Builder): AndroidInjector.Factory<out Activity>
}
