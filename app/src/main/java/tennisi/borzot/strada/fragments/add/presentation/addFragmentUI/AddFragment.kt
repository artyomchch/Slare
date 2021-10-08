package tennisi.borzot.strada.fragments.add.presentation.addFragmentUI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import tennisi.borzot.strada.R
import tennisi.borzot.strada.databinding.FragmentAddBinding
import tennisi.borzot.strada.fragments.add.presentation.carItemUI.CarItemActivity
import tennisi.borzot.strada.fragments.add.presentation.carItemUI.CarItemFragment


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
            if (isOnePaneMode()){
                val intent = context?.let { it1 -> CarItemActivity.newIntentAddItem(it1.applicationContext) }
                startActivity(intent)
            } else {
                launchFragment(CarItemFragment.newInstanceAddItem())
            }


        }

        return binding.root
    }

    private fun isOnePaneMode(): Boolean{
        return binding.carItemContainer == null
    }

    private fun launchFragment(fragment: Fragment){
        parentFragmentManager.apply {
            popBackStack()
            beginTransaction()
                .replace(R.id.car_item_container, fragment)
                .addToBackStack(null)
                .commit()
        }
    }



    private fun setupRecyclerView() {

        with(binding.carsRecycler) {
            carsListAdapter = CarsListAdapter()
            adapter = carsListAdapter
            recycledViewPool.setMaxRecycledViews(CarsListAdapter.VIEW_TYPE_ENABLED, CarsListAdapter.MAX_POOL_SIZE)
            recycledViewPool.setMaxRecycledViews(CarsListAdapter.VIEW_TYPE_DISABLED, CarsListAdapter.MAX_POOL_SIZE)
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
            if (isOnePaneMode()){
                val intent = context?.let { it1 -> CarItemActivity.newIntentEditItem(it1.applicationContext, it.id) }
                startActivity(intent)
            }
            else{
                launchFragment(CarItemFragment.newInstanceEditItem(it.id))
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
}