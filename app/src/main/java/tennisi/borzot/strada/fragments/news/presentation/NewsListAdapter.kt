package tennisi.borzot.strada.fragments.news.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import tennisi.borzot.strada.R
import tennisi.borzot.strada.fragments.add.domain.entity.CarItem
import tennisi.borzot.strada.network.pojo.Article
import tennisi.borzot.strada.utils.DateUtils

class NewsListAdapter : ListAdapter<Article, NewsItemViewHolder>(NewsItemDiffCallback()) {

    var onNewsItemClickListener: ((Article) -> Unit)? = null
    var onNewsItemLongClickListener: ((Article) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: NewsItemViewHolder, position: Int) {
        val newsItem = getItem(position)
        Glide.with(viewHolder.itemView)
            .load(newsItem.urlToImage)
            .centerCrop()
            .into(viewHolder.image)
        viewHolder.author.text = newsItem.author
        viewHolder.publishTime.text = DateUtils.dateFormat(newsItem.publishedAt)
        viewHolder.title.text = newsItem.title
        viewHolder.description.text = newsItem.description
        viewHolder.source.text = newsItem.source.name
        (viewHolder.itemView.resources.getString(R.string.marker_dot) + " " + DateUtils.dateToTimeFormat(newsItem.publishedAt)).also {
            viewHolder.time.text = it
        }

        viewHolder.itemView.setOnClickListener {
            onNewsItemClickListener?.invoke(newsItem)
        }

        viewHolder.itemView.setOnLongClickListener {
            onNewsItemLongClickListener?.invoke(newsItem)
            true
        }
    }

}