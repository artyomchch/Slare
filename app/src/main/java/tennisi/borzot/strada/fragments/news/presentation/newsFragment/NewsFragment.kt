package tennisi.borzot.strada.fragments.news.presentation.newsFragment

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import tennisi.borzot.strada.R
import tennisi.borzot.strada.StradaApplication
import tennisi.borzot.strada.databinding.FragmentNewsBinding
import tennisi.borzot.strada.fragments.add.presentation.ViewModelFactory
import javax.inject.Inject


class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val binding: FragmentNewsBinding
        get() = _binding ?: throw RuntimeException("FragmentNewsBinding == null")


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[(NewsFragmentViewModel::class.java)]
    }

    private lateinit var newsListAdapter: NewsListAdapter

    private val component by lazy {
        (requireActivity().application as StradaApplication).component
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNewsBinding.inflate(inflater, container, false)

        setupRecyclerView()


        viewModel.newsItems.observe(viewLifecycleOwner) {
            newsListAdapter.submitList(it)
            with(binding) {
                swipeRefreshLayout.isRefreshing = false
            }

        }

        swipeRefreshListener()
        setupClickListener()
        setupOnLongClickListener()
        stateLoadingListener()

        return binding.root
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    private fun stateLoadingListener() {
        viewModel.stateLoading.observe(viewLifecycleOwner) {
            if (it) binding.swipeRefreshLayout.isRefreshing = true
        }
    }


    private fun swipeRefreshListener() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = true
            viewModel.updateNewsList()
        }
    }

    private fun setupClickListener() {
        newsListAdapter.onNewsItemClickListener = {
            findNavController().navigate(NewsFragmentDirections.actionNewsFragmentToSourceFragment(it.url, it.imageUrl, it.source))
        }
    }

    private fun setupOnLongClickListener() {
        newsListAdapter.onNewsItemLongClickListener = { url_news ->
            copyToClipboard(url_news.url)
            Snackbar.make(requireView(), getString(R.string.news_url_clipboard_text), Snackbar.LENGTH_INDEFINITE)
                .setDuration(5000)
                .setAction(getString(R.string.share)) {
                    sendingBinaryContent(url_news.url)
                }
                .show()
        }
    }

    private fun sendingBinaryContent(url_news: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, url_news)
            type = "text/plain"
        }
        startActivity(Intent.createChooser(sendIntent, null))
    }

    private fun copyToClipboard(url_news: String) {
        val myClipboard: ClipboardManager = activity?.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText(getString(R.string.text_copied_label), url_news)
        myClipboard.setPrimaryClip(clip)
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