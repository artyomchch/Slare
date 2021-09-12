package tennisi.borzot.strada.fragments.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import tennisi.borzot.strada.R
import tennisi.borzot.strada.databinding.FragmentNewsViewPagerBinding
import tennisi.borzot.strada.fragments.news.model.User


class NewsFragmentViewPager : Fragment(), Navigator {

    private lateinit var binding: FragmentNewsViewPagerBinding
    private val actions = mutableListOf<() -> Unit>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsViewPagerBinding.inflate(inflater, container, false)

        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                goBack()
            }
        })

        if (savedInstanceState == null) {
            childFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, NewsFragment())
                .commit()
        }

        return binding.root
    }

    override fun showDetails(user: User) {
        runWhenActive {
            childFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, UserDetailsFragment.newInstance(user.id))
                .addToBackStack(null)
                .commit()
        }

    }

    override fun goBack() {
        runWhenActive {
            if (childFragmentManager.popBackStackImmediate()){
                childFragmentManager.popBackStack()
            } else
                activity?.finish()
        }


    }

    override fun toast(messageRes: Int) {
        Toast.makeText(context, messageRes, Toast.LENGTH_SHORT).show()
    }

    private fun runWhenActive(action: () -> Unit) {
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)) {
            action()
        } else {
            actions += action
        }
    }
}