package tennisi.borzot.strada.fragments.news.presentation

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tennisi.borzot.strada.network.pojo.NewsItem

class NewsListAdapter: RecyclerView.Adapter<NewsListAdapter.NewsItemViewHolder>() {

    val list = listOf<NewsItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class NewsItemViewHolder(view: View): RecyclerView.ViewHolder(view)
}