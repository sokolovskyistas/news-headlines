package com.example.stanislav.newsapp.ui

import com.example.stanislav.newsapp.di.ActivityScoped
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ActivityScoped
@Subcomponent(modules = [NewsListModule::class])
interface NewsListComponent : AndroidInjector<NewsListActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<NewsListActivity>()
}

@Module
class NewsListModule {

    @ActivityScoped
    @Provides
    fun providesView(activity: NewsListActivity): ViewI = activity
}