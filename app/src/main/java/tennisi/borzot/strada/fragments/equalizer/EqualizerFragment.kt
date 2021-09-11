package tennisi.borzot.strada.fragments.equalizer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import tennisi.borzot.strada.R
import tennisi.borzot.strada.databinding.FragmentEqualizerBinding


class EqualizerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentEqualizerBinding by lazy(LazyThreadSafetyMode.NONE) { FragmentEqualizerBinding.inflate(inflater, container, false) }

        binding.toolbarEqualizerFragment.toolbar.title = getString(R.string.equalizer)

        Glide.with(this)
            .asGif()
            .load("https://media4.giphy.com/media/gG6OcTSRWaSis/giphy.gif")
            .into(binding.fixLoad)

        return binding.root
    }

}