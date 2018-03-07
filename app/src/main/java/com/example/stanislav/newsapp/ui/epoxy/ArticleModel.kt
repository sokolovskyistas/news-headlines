package com.example.stanislav.newsapp.ui.epoxy

import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.stanislav.newsapp.R

class ArticleModel(id: Long,
                   private val title: String,
                   private val description: String?) : EpoxyModelWithHolder<ArticleViewHolder>(id) {

    override fun getDefaultLayout() = R.layout.article_layout

    override fun createNewHolder() = ArticleViewHolder()

    override fun bind(holder: ArticleViewHolder) {
        holder.setTitle(title)
        holder.setBody(description)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false

        other as ArticleModel

        if (title != other.title) return false
        if (description != other.description) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + (description?.hashCode() ?: 0)
        return result
    }


}

class ArticleViewHolder : EpoxyHolder() {

    private lateinit var title: TextView
    private lateinit var body: TextView

    override fun bindView(itemView: View) {
        title = itemView.findViewById(R.id.title)
        body = itemView.findViewById(R.id.subtitle)
    }

    fun setTitle(titleString: String) {
        title.text = titleString
    }

    fun setBody(bodyString: String?) {
        body.text = bodyString
    }
}