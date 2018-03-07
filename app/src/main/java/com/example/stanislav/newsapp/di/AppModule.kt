package com.example.stanislav.newsapp.di

import android.content.Context
import com.example.stanislav.newsapp.app.NewsApplication
import com.example.stanislav.newsapp.ui.NewsListComponent
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [NewsListComponent::class])
class AppModule {

    @Provides
    @Singleton
    fun providesGson(): Gson = Gson()

    @Provides
    @Singleton
    fun providesApplicationContext(application: NewsApplication): Context = application
}