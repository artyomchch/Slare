package tennisi.borzot.strada.fragments.add.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import tennisi.borzot.strada.databinding.FragmentAddBinding


class AddFragment : Fragment() {

    private lateinit var adapter: CarsListAdapter
    private lateinit var viewModel: AddFragmentViewModel

    private var _binding: FragmentAddBinding? = null
    private val binding: FragmentAddBinding
        get() = _binding ?: throw RuntimeException("FragmentAddBinding == null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddBinding.inflate(inflater, container, false)

        setupRecyclerView()
        viewModel = ViewModelProvider(this)[(AddFragmentViewModel::class.java)]
        viewModel.carList.observe(viewLifecycleOwner) {
            adapter.carsList = it
        }

        return binding.root
    }

    private fun setupRecyclerView() {
        adapter = CarsListAdapter()
        binding.carsRecycler.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}