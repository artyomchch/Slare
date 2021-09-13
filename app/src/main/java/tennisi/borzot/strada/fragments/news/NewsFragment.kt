package tennisi.borzot.strada.fragments.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import tennisi.borzot.strada.databinding.FragmentNewsBinding
import tennisi.borzot.strada.fragments.news.promisses.EmptyResult
import tennisi.borzot.strada.fragments.news.promisses.ErrorResult
import tennisi.borzot.strada.fragments.news.promisses.PendingResult
import tennisi.borzot.strada.fragments.news.promisses.SuccessResult
import tennisi.borzot.strada.fragments.news.utils.factory
import tennisi.borzot.strada.fragments.news.utils.navigator
import tennisi.borzot.strada.fragments.news.viewModel.NewsViewModel


class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding
    private lateinit var adapter: UsersAdapter

    private val viewModel: NewsViewModel by viewModels { factory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding =  FragmentNewsBinding.inflate(inflater, container, false)
        adapter = UsersAdapter(viewModel)

        viewModel.users.observe(viewLifecycleOwner,Observer {
            hideAll()
            when (it){
                is SuccessResult ->{
                    binding.userRecycler.visibility = View.VISIBLE
                    adapter.users = it.data
                }
                is ErrorResult ->{
                    binding.tryAgainContainer.visibility = View.VISIBLE
                }
                is PendingResult ->{
                    binding.progressBar.visibility = View.VISIBLE
                }
                is EmptyResult ->{
                    binding.noUsersTextView.visibility = View.VISIBLE
                }
            }
        })

        viewModel.actionShowDetails.observe(viewLifecycleOwner, Observer {
            it.getValue()?.let { user -> navigator().showDetails(user) }
        })
        viewModel.actionShowToast.observe(viewLifecycleOwner, Observer {
            it.getValue()?.let { messageRes -> navigator().toast(messageRes) }
        })

        val layoutManager = LinearLayoutManager(context)
        binding.userRecycler.layoutManager = layoutManager
        binding.userRecycler.adapter = adapter
        val itemAnimator = binding.userRecycler.itemAnimator
        if (itemAnimator is DefaultItemAnimator){
            itemAnimator.supportsChangeAnimations = false
        }


        return binding.root
    }

    private fun hideAll(){
        binding.userRecycler.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
        binding.tryAgainContainer.visibility = View.GONE
        binding.noUsersTextView.visibility = View.GONE
    }

}