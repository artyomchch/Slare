package tennisi.borzot.strada.fragments.add.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import tennisi.borzot.strada.R
import tennisi.borzot.strada.databinding.FragmentAddBinding


class AddFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentAddBinding by lazy(LazyThreadSafetyMode.NONE) { FragmentAddBinding.inflate(inflater, container, false) }
        binding.toolbarCarFragment.toolbar.title = getString(R.string.vehicle)


        return binding.root
    }


}