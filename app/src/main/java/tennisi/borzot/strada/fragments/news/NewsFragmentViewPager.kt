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


class NewsFragmentViewPager : Fragment() {

    private lateinit var binding: FragmentNewsViewPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsViewPagerBinding.inflate(inflater, container, false)



        return binding.root
    }

}