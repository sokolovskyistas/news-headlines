package com.example.stanislav.newsapp.ui

import com.example.stanislav.newsapp.model.PageData
import com.example.stanislav.newsapp.ui.epoxy.ArticleModel
import com.example.stanislav.newsapp.ui.epoxy.InfoModel
import javax.inject.Inject

class ViewDataConverter @Inject constructor() {

    fun convert(page: List<PageData>): List<ArticleModel> {
        return page
                .map { it.toArticles() }
                .flatten()
                .distinctBy { it.id() }
                .let {
                    when (page.lastOrNull()) {
                        is PageData.Page -> it.plus(InfoModel())
                        is PageData.LastPage -> it.plus(InfoModel())
                    }
                }
    }

    private fun PageData.toArticles() =
            data.map { article ->
                ArticleModel(id = article.title.hashCode().toLong(),
                        title = article.title,
                        description = article.description)
            }
}