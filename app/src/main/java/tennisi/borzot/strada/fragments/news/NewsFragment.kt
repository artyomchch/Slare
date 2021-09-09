package tennisi.borzot.strada.fragments.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import tennisi.borzot.strada.database.MyApplication
import tennisi.borzot.strada.databinding.FragmentNewsBinding
import tennisi.borzot.strada.fragments.news.model.User
import tennisi.borzot.strada.fragments.news.model.UsersService
import tennisi.borzot.strada.fragments.news.utils.factory
import tennisi.borzot.strada.fragments.news.utils.navigator
import tennisi.borzot.strada.fragments.news.viewModel.NewsViewModel


class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding
    private lateinit var adapter: UsersAdapter

    private val viewModel: NewsViewModel by viewModels {factory()}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)

        adapter = UsersAdapter(object : UserActionListener {
            override fun onUserMove(user: User, moveBy: Int) {
               viewModel.moveUser(user, moveBy)
            }

            override fun onUserDelete(user: User) {
                viewModel.deleteUser(user)
            }

            override fun onUserDetails(user: User) {
                navigator().showDetails(user)
            }
        })

        viewModel.users.observe(viewLifecycleOwner, Observer {
            adapter.users = it
        } )

        val layoutManager = LinearLayoutManager(context)
        binding.userRecycler.layoutManager = layoutManager
        binding.userRecycler.adapter = adapter

        return binding.root
    }

}