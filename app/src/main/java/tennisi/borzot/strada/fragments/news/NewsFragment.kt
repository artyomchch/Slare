package tennisi.borzot.strada.fragments.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.javafaker.App
import tennisi.borzot.strada.database.MyApplication
import tennisi.borzot.strada.databinding.FragmentNewsBinding
import tennisi.borzot.strada.fragments.news.model.User
import tennisi.borzot.strada.fragments.news.model.UsersListener
import tennisi.borzot.strada.fragments.news.model.UsersService


class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: UsersAdapter

    private val usersService: UsersService
        get() = (requireActivity().applicationContext as MyApplication).usersService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)

        adapter = UsersAdapter(object : UserActionListener{
            override fun onUserMove(user: User, moveBy: Int) {
               usersService.moveUser(user, moveBy)
            }

            override fun onUserDelete(user: User) {
                usersService.deleteUser(user)
            }

            override fun onUserDetails(user: User) {
               Toast.makeText(context, "User ${user.name}", Toast.LENGTH_LONG).show()
            }
        })

        val layoutManager = LinearLayoutManager(context)
        binding.userRecycler.layoutManager = layoutManager
        binding.userRecycler.adapter = adapter

        usersService.addListener(usersListener)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        usersService.removeListener(usersListener)
    }

    private val usersListener: UsersListener = {
        adapter.users = it
    }


}