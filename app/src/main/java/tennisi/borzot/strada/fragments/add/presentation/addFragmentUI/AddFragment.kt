package tennisi.borzot.strada.fragments.add.presentation.addFragmentUI

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import tennisi.borzot.strada.R
import tennisi.borzot.strada.databinding.FragmentAddBinding
import tennisi.borzot.strada.fragments.add.presentation.carItemUI.CarItemFragment


class AddFragment : Fragment() {

    private lateinit var carsListAdapter: CarsListAdapter
    private lateinit var viewModel: AddFragmentViewModel
    private lateinit var onItemSelectedListener: OnItemSelectedListener

    private var _binding: FragmentAddBinding? = null
    private val binding: FragmentAddBinding
        get() = _binding ?: throw RuntimeException("FragmentAddBinding == null")


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnItemSelectedListener) {
            Log.d("onAttach", "onAttach:  work")
            onItemSelectedListener = context
        } else {
            throw RuntimeException("Activity must implement OnItemSelectedListener")
        }
    }

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
            if (isOnePaneMode()) {
                launchFragment(R.id.main_car_add_container, CarItemFragment.newInstanceAddItem())
                onItemSelectedListener.onItemSelected()
            } else {
                launchFragment(R.id.car_item_container, CarItemFragment.newInstanceAddItem())
            }
        }

        return binding.root
    }


    private fun isOnePaneMode(): Boolean {
        return binding.carItemContainer == null
    }

    private fun launchFragment(currentFragment: Int, fragment: Fragment) {
        parentFragmentManager.apply {
            popBackStack()
            beginTransaction()
                .replace(currentFragment, fragment)
                .addToBackStack(null)
                .commit()
        }
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
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
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
            if (isOnePaneMode()) {
                launchFragment(R.id.main_car_add_container, CarItemFragment.newInstanceEditItem(it.id))
                onItemSelectedListener.onItemSelected()
            } else {
                launchFragment(R.id.car_item_container, CarItemFragment.newInstanceEditItem(it.id))
            }

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

    interface OnItemSelectedListener {

        fun onItemSelected()

    }
}