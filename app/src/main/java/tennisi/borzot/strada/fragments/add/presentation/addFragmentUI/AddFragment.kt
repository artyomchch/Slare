package tennisi.borzot.strada.fragments.add.presentation.addFragmentUI

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import tennisi.borzot.strada.databinding.FragmentAddBinding
import tennisi.borzot.strada.fragments.add.domain.entity.ScreenAddMode


class AddFragment : Fragment() {

    private lateinit var carsListAdapter: CarsListAdapter
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
            carsListAdapter.submitList(it)
        }
        binding.carAddFab.setOnClickListener {
            findNavController().navigate(AddFragmentDirections.actionAddFragmentToCarItemFragment(ScreenAddMode.ADD, -1))
        }

        return binding.root
    }

    private fun setupRecyclerView() {

        with(binding.carsRecycler) {
            carsListAdapter = CarsListAdapter()
            adapter = carsListAdapter
            recycledViewPool.apply {
                setMaxRecycledViews(CarsListAdapter.VIEW_TYPE_ENABLED, CarsListAdapter.MAX_POOL_SIZE)
                setMaxRecycledViews(CarsListAdapter.VIEW_TYPE_DISABLED, CarsListAdapter.MAX_POOL_SIZE)
            }
        }

        setupOnLongClickListener()
        setupClickListener()
        setupSwipeListener()
    }

    private fun setupSwipeListener() {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = carsListAdapter.currentList[viewHolder.layoutPosition]
                viewModel.deleteCarItem(item)
            }
        }

        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.carsRecycler)
    }

    private fun setupClickListener() {
        carsListAdapter.onCarItemClickListener = {
            findNavController().navigate(AddFragmentDirections.actionAddFragmentToCarItemFragment(ScreenAddMode.EDIT, it.id))
        }
    }

    private fun setupOnLongClickListener() {
        carsListAdapter.onCarItemLongClickListener = {
            viewModel.changeEnableState(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}