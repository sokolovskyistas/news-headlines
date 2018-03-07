package com.example.stanislav.newsapp.http

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceUtils {
    fun getRetrofit(url: String): Retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient())
            .build()

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .readTimeout(1, TimeUnit.MINUTES)
                .addNetworkInterceptor(StethoInterceptor())
                .build()
    }
}