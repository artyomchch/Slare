package tennisi.borzot.strada.fragments.news.presentation.newsFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import tennisi.borzot.strada.databinding.ItemLoaderBinding

typealias TryAgainAction = () -> Unit

class LoaderStateAdapter(
    private val tryAgainAction: TryAgainAction
) : LoadStateAdapter<LoaderStateAdapter.LoaderViewHolder>() {


    override fun onBindViewHolder(holder: LoaderViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoaderViewHolder {
        val binding = ItemLoaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoaderViewHolder(binding, null, tryAgainAction)
    }


    class LoaderViewHolder(
        val binding: ItemLoaderBinding, private val swipeRefreshLayout: SwipeRefreshLayout?, private val tryAgainAction: TryAgainAction
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.tryAgainButton.setOnClickListener { tryAgainAction() }
        }

        fun bind(loadState: LoadState) = with(binding) {
            animationView.isVisible  = loadState is LoadState.Error
            messageTextView.isVisible = loadState is LoadState.Error
            tryAgainButton.isVisible = loadState is LoadState.Error
            if (swipeRefreshLayout != null) {
                swipeRefreshLayout.isRefreshing = loadState is LoadState.Loading
                progressBar.isVisible = false
            } else {
                progressBar.isVisible = loadState is LoadState.Loading
                animationView.isVisible = loadState is LoadState.Loading

            }
        }

    }
}
