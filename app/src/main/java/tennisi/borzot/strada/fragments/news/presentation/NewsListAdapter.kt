package tennisi.borzot.strada.fragments.news.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tennisi.borzot.strada.R
import tennisi.borzot.strada.network.pojo.Article
import tennisi.borzot.strada.utils.DateUtils

class NewsListAdapter : RecyclerView.Adapter<NewsListAdapter.NewsItemViewHolder>() {

    var newsList = listOf<Article>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: NewsItemViewHolder, position: Int) {
        val newsItem = newsList[position]
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
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    class NewsItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.image_news)
        val author: TextView = view.findViewById(R.id.author_text)
        val publishTime: TextView = view.findViewById(R.id.published_at)
        val title: TextView = view.findViewById(R.id.title_news)
        val description: TextView = view.findViewById(R.id.desc_news)
        val source: TextView = view.findViewById(R.id.source_news)
        val time: TextView = view.findViewById(R.id.time_news)
    }
}