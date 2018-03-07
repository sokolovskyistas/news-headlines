package com.example.stanislav.newsapp.ui.epoxy

import com.airbnb.epoxy.EpoxyController
import javax.inject.Inject

class NewsListEpoxyController @Inject constructor(): EpoxyController() {

    private var items = emptyList<ArticleModel>()

    override fun buildModels() {
        items.forEach { item -> add(item) }
    }

    fun setData(items: List<ArticleModel>) {
        this.items = items
        requestModelBuild()
    }
}