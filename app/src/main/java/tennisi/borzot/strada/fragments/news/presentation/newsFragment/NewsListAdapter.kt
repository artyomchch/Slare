package tennisi.borzot.strada.fragments.news.presentation.newsFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import tennisi.borzot.strada.R
import tennisi.borzot.strada.databinding.ItemNewsBinding
import tennisi.borzot.strada.network.pojo.Article
import tennisi.borzot.strada.utils.DateUtils

class NewsListAdapter : ListAdapter<Article, NewsItemViewHolder>(NewsItemDiffCallback()) {

    var onNewsItemClickListener: ((Article) -> Unit)? = null
    var onNewsItemLongClickListener: ((Article) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsItemViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: NewsItemViewHolder, position: Int) {
        val newsItem = getItem(position)
        with(viewHolder.binding){
            Glide.with(root)
                .load(newsItem.urlToImage)
                .centerCrop()
                .into(imageNews)
            authorText.text = newsItem.author
            publishedAt.text = DateUtils.dateFormat(newsItem.publishedAt)
            titleNews.text = newsItem.title
            descNews.text = newsItem.description
            sourceNews.text = newsItem.source.name
            (viewHolder.itemView.resources.getString(R.string.marker_dot) + " " + DateUtils.dateToTimeFormat(newsItem.publishedAt)).also {
                timeNews.text = it
            }

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