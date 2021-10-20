package tennisi.borzot.strada.fragments.news.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import tennisi.borzot.strada.databinding.FragmentNewsBinding

class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val binding: FragmentNewsBinding
        get() = _binding ?: throw RuntimeException("FragmentNewsBinding == null")

    private lateinit var newsListAdapter: NewsListAdapter

    private lateinit var viewModel: NewsFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)

        setupRecyclerView()
        viewModel = ViewModelProvider(this)[NewsFragmentViewModel::class.java]

        viewModel.newsList.observe(viewLifecycleOwner) {
            newsListAdapter.submitList(it)
            binding.progressBarNews.visibility = View.GONE
        }

        setupClickListener()
        setupOnLongClickListener()

        return binding.root
    }

    private fun setupClickListener() {
        newsListAdapter.onNewsItemClickListener = {
            Toast.makeText(context, it.url, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupOnLongClickListener() {
        newsListAdapter.onNewsItemLongClickListener = {
            Toast.makeText(context, it.url, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupRecyclerView() {
        newsListAdapter = NewsListAdapter()
        binding.newsRecycler.adapter = newsListAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}