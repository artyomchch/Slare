package tennisi.borzot.strada.fragments.news.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import tennisi.borzot.strada.R
import tennisi.borzot.strada.databinding.FragmentNewsBinding
import tennisi.borzot.strada.network.pojo.Article
import tennisi.borzot.strada.utils.DateUtils


class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding
    private lateinit var adapter: NewsListAdapter

    private lateinit var viewModel: NewsFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)

        setupRecyclerView()
        viewModel = ViewModelProvider(this)[NewsFragmentViewModel::class.java]
        viewModel.newsResponse.observe(viewLifecycleOwner) {
           // showList(it)
            adapter.newsList = it
        }


        return binding.root
    }

    private fun setupRecyclerView(){
        adapter = NewsListAdapter()
        binding.newsRecycler.adapter = adapter
    }

//    private fun showList(list: List<Article>){
//        for (newsItem in list){
//            val layoutId = R.layout.item_news
//            val view = LayoutInflater.from(context).inflate(layoutId, binding.llShopLsit, false)
//
//            val image = view.findViewById<ImageView>(R.id.image_news)
//            val author = view.findViewById<TextView>(R.id.author_text)
//            val publishTime = view.findViewById<TextView>(R.id.published_at)
//            val title = view.findViewById<TextView>(R.id.title_news)
//            val description = view.findViewById<TextView>(R.id.desc_news)
//            val source = view.findViewById<TextView>(R.id.source_news)
//            val time = view.findViewById<TextView>(R.id.time_news)
//
//            Glide.with(this)
//                .load(newsItem.urlToImage)
//                .centerCrop()
//                .into(image)
//            author.text = newsItem.author
//            publishTime.text = DateUtils.dateFormat(newsItem.publishedAt)
//            title.text = newsItem.title
//            description.text = newsItem.description
//            source.text = newsItem.source.name
//            (resources.getString(R.string.marker_dot) + " " + DateUtils.dateToTimeFormat(newsItem.publishedAt)).also { time.text = it }
//            view.setOnLongClickListener {
//                Toast.makeText(context, newsItem.url, Toast.LENGTH_LONG).show()
//                true
//            }
//
//            binding.llShopLsit.addView(view)
//        }
//    }


}