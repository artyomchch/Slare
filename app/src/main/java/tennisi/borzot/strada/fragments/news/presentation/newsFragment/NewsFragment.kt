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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
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


    private lateinit var newsPagingAdapter: NewsPagingAdapter
    private lateinit var loaderStateAdapter: LoaderStateAdapter
    private lateinit var mainLoadStateHolder: LoaderStateAdapter.LoaderViewHolder

    private val component by lazy {
        (requireActivity().application as StradaApplication).component
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNewsBinding.inflate(inflater, container, false)

        setupRecyclerView()

        setupClickListener()
        setupOnLongClickListener()
        setupSwipeToRefresh()

        return binding.root
    }

    private fun observeNews() {
        lifecycleScope.launch {
            viewModel.newsFlow.collectLatest { pagingData ->
                newsPagingAdapter.submitData(pagingData)
            }
        }
    }

    private fun observeLoadState() {
        lifecycleScope.launch {
            newsPagingAdapter.loadStateFlow.collectLatest { state ->
                mainLoadStateHolder.bind(state.refresh)
            }
        }
    }

    private fun setupSwipeToRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            newsPagingAdapter.refresh()
        }
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }


    private fun setupClickListener() {
        newsPagingAdapter.onNewsItemClickListener = {
            findNavController().navigate(NewsFragmentDirections.actionNewsFragmentToSourceFragment(it.url, it.imageUrl, it.source))
        }
    }

    private fun setupOnLongClickListener() {
        newsPagingAdapter.onNewsItemLongClickListener = { url_news ->
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
        newsPagingAdapter = NewsPagingAdapter()
        val tryAgainAction: TryAgainAction = { newsPagingAdapter.retry() }
        loaderStateAdapter = LoaderStateAdapter(tryAgainAction)
        binding.newsRecycler.adapter = newsPagingAdapter.withLoadStateFooter(loaderStateAdapter)

        mainLoadStateHolder = LoaderStateAdapter.LoaderViewHolder(
            binding.loadStateView,
            binding.swipeRefreshLayout,
            tryAgainAction
        )

        observeNews()
        observeLoadState()

    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding.newsRecycler.adapter = null
        _binding = null
    }
}