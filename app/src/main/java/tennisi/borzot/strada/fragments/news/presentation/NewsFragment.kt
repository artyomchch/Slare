package tennisi.borzot.strada.fragments.news.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import tennisi.borzot.strada.databinding.FragmentNewsBinding

class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val binding: FragmentNewsBinding
        get() = _binding ?: throw RuntimeException("FragmentNewsBinding == null")

    private lateinit var adapter: NewsListAdapter

    private lateinit var viewModel: NewsFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)

        setupRecyclerView()
        viewModel = ViewModelProvider(this)[NewsFragmentViewModel::class.java]

        viewModel.newsList.observe(viewLifecycleOwner) {
            adapter.newsList = it
            binding.progressBarNews.visibility = View.GONE
        }

        return binding.root
    }

    private fun setupRecyclerView() {
        adapter = NewsListAdapter()
        binding.newsRecycler.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}