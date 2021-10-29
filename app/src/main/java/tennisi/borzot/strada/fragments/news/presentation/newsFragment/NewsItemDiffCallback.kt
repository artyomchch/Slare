package tennisi.borzot.strada.fragments.news.presentation.newsFragment

import androidx.recyclerview.widget.DiffUtil
import tennisi.borzot.strada.network.pojo.Article


class NewsItemDiffCallback : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }

}
