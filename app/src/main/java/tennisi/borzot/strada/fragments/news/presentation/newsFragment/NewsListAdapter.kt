package tennisi.borzot.strada.fragments.news.presentation.newsFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import tennisi.borzot.strada.R
import tennisi.borzot.strada.databinding.ItemNewsBinding
import tennisi.borzot.strada.fragments.news.domain.entity.NewsItem

class NewsListAdapter : ListAdapter<NewsItem, NewsItemViewHolder>(NewsItemDiffCallback()) {

    var onNewsItemClickListener: ((NewsItem) -> Unit)? = null
    var onNewsItemLongClickListener: ((NewsItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsItemViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: NewsItemViewHolder, position: Int) {
        val newsItem = getItem(position)
        with(viewHolder.binding) {
            Glide.with(root)
                .load(newsItem.imageUrl)
                .placeholder(R.drawable.ic_baseline_broken_image_24)
                .centerCrop()
                .into(imageNews)
            publishedAt.text = newsItem.date
            titleNews.text = newsItem.title
            descNews.text = newsItem.description
            sourceNews.text = newsItem.source
            timeNews.text = newsItem.time



            root.setOnClickListener {
                onNewsItemClickListener?.invoke(newsItem)
            }

            root.setOnLongClickListener {
                onNewsItemLongClickListener?.invoke(newsItem)
                true
            }
        }

    }

}