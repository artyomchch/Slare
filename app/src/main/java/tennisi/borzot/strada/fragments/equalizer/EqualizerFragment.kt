package tennisi.borzot.strada.fragments.equalizer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import tennisi.borzot.strada.databinding.FragmentEqualizerBinding


class EqualizerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentEqualizerBinding by lazy(LazyThreadSafetyMode.NONE) { FragmentEqualizerBinding.inflate(inflater, container, false) }


        Glide.with(this)
            .asGif()
            .load("https://media4.giphy.com/media/gG6OcTSRWaSis/giphy.gif")
            .into(binding.fixLoad)

        binding.fixLoad.setOnClickListener {
            doCrash()
        }

        return binding.root
    }

    fun doCrash() {
        Toast.makeText(requireContext(), "Google Analytics is watching you 0_0", Toast.LENGTH_SHORT).show()
        throw RuntimeException("Test Crash")
    }

}