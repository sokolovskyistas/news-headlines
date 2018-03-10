package com.example.stanislav.newsapp.di

import com.example.stanislav.newsapp.app.NewsApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityBindingModule::class, ServicesModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: NewsApplication): Builder

        fun build(): AppComponent
    }

    fun inject(application: NewsApplication)
}