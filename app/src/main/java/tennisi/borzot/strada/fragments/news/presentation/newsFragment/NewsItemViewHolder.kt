package tennisi.borzot.strada.fragments.news.presentation.newsFragment

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tennisi.borzot.strada.R

class NewsItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val image: ImageView = view.findViewById(R.id.image_news)
    val author: TextView = view.findViewById(R.id.author_text)
    val publishTime: TextView = view.findViewById(R.id.published_at)
    val title: TextView = view.findViewById(R.id.title_news)
    val description: TextView = view.findViewById(R.id.desc_news)
    val source: TextView = view.findViewById(R.id.source_news)
    val time: TextView = view.findViewById(R.id.time_news)
}