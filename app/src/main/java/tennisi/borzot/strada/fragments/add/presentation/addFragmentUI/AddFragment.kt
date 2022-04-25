package tennisi.borzot.strada.fragments.add.presentation.addFragmentUI

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import tennisi.borzot.strada.StradaApplication
import tennisi.borzot.strada.databinding.FragmentAddBinding
import tennisi.borzot.strada.fragments.add.domain.entity.ScreenAddMode
import tennisi.borzot.strada.fragments.add.presentation.ViewModelFactory
import javax.inject.Inject


class AddFragment : Fragment() {

    private lateinit var carsListAdapter: CarsListAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[(AddFragmentViewModel::class.java)]
    }


    private var _binding: FragmentAddBinding? = null
    private val binding: FragmentAddBinding
        get() = _binding ?: throw RuntimeException("FragmentAddBinding == null")


    private val component by lazy {
        (requireActivity().application as StradaApplication).component
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddBinding.inflate(inflater, container, false)
        setupRecyclerView()
        observeRecycler()
        navigateItem()
        observeState()

        return binding.root
    }

    private fun observeState() {
        viewModel.observerItems.observe(viewLifecycleOwner) { state ->
            when (state) {
                is CarObserver.Warning -> {
                    binding.animationView.visibility = View.VISIBLE
                    binding.textAddCarWarning.visibility = View.VISIBLE
                }
                is CarObserver.Success -> {
                    binding.animationView.visibility = View.GONE
                    binding.textAddCarWarning.visibility = View.GONE
                }
            }
        }
    }


    private fun observeRecycler() {
        viewModel.carList.observe(viewLifecycleOwner) {
            carsListAdapter.submitList(it)
            viewModel.observe()
        }
    }

    private fun navigateItem() {
        binding.carAddFab.setOnClickListener {
            findNavController().navigate(AddFragmentDirections.actionAddFragmentToCarItemFragment(ScreenAddMode.ADD, -1))
        }
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
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