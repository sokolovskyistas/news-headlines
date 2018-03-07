package com.example.stanislav.newsapp.di

import com.example.stanislav.newsapp.http.HeadlinesService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ServicesModule {

    @Singleton
    @Provides
    fun providesHeadlinesService() = HeadlinesService.getService()
}