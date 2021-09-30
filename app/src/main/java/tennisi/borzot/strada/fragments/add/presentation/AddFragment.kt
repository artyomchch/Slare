package tennisi.borzot.strada.fragments.add.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import tennisi.borzot.strada.R
import tennisi.borzot.strada.databinding.FragmentAddBinding


class AddFragment : Fragment() {

    private lateinit var viewModel: AddFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentAddBinding by lazy(LazyThreadSafetyMode.NONE) { FragmentAddBinding.inflate(inflater, container, false) }
        binding.toolbarCarFragment.toolbar.title = getString(R.string.vehicle)

        viewModel = ViewModelProvider(this)[(AddFragmentViewModel::class.java)]
        viewModel.carList.observe(viewLifecycleOwner){

        }

        return binding.root
    }


}