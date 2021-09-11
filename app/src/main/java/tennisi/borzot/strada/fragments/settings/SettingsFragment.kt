package tennisi.borzot.strada.fragments.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import tennisi.borzot.strada.R
import tennisi.borzot.strada.databinding.FragmentSettingsBinding


class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentSettingsBinding by lazy(LazyThreadSafetyMode.NONE) { FragmentSettingsBinding.inflate(inflater, container, false) }

        binding.toolbarSettingsFragment.toolbar.title = getString(R.string.settings)
        Glide.with(this)
            .asGif()
            .load("https://i.imgur.com/Mn9dJcJ.gif")
            .into(binding.devLoad)

        return binding.root
    }


}