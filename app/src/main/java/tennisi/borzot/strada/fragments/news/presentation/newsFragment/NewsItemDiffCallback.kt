package tennisi.borzot.strada.fragments.news.presentation.newsFragment

import androidx.recyclerview.widget.DiffUtil
import tennisi.borzot.strada.fragments.news.domain.entity.NewsItem


class NewsItemDiffCallback : DiffUtil.ItemCallback<NewsItem>() {
    override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
        return oldItem == newItem
    }

}
