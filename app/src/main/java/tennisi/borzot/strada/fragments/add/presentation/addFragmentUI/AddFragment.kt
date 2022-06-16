package tennisi.borzot.strada.fragments.add.presentation.addFragmentUI

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import tennisi.borzot.strada.StradaApplication
import tennisi.borzot.strada.databinding.FragmentAddBinding
import tennisi.borzot.strada.fragments.add.domain.entity.ScreenAddMode
import tennisi.borzot.strada.fragments.add.presentation.ViewModelFactory
import tennisi.borzot.strada.fragments.add.presentation.addFragmentUI.setuprecycler.CarsListAdapter
import tennisi.borzot.strada.fragments.add.presentation.addFragmentUI.swipelistener.MyButton
import tennisi.borzot.strada.fragments.add.presentation.addFragmentUI.swipelistener.MySwipeHelper
import tennisi.borzot.strada.fragments.add.presentation.addFragmentUI.swipelistener.SwipeListenerButton
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
            }
        }

        setupOnLongClickListener()
        setupClickListener()
        setupSwipeListener()
    }

    private fun setupSwipeListener() {
        object : MySwipeHelper(requireContext(), binding.carsRecycler, 200) {
            override fun instantiateMyButton(viewHolder: RecyclerView.ViewHolder, buffer: MutableList<MyButton>) {
                val item = carsListAdapter.currentList[viewHolder.layoutPosition]
                buffer.add(
                    MyButton(requireContext(), "Delete", 60, 0, Color.parseColor("#FF3C30"),
                        object : SwipeListenerButton {
                            override fun onClick(pos: Int) {
                                viewModel.deleteCarItem(item)
                            }
                        })
                )
                buffer.add(
                    MyButton(requireContext(), "Edit", 60, 0, Color.parseColor("#FF9502"),
                        object : SwipeListenerButton {
                            override fun onClick(pos: Int) {
                                findNavController().navigate(AddFragmentDirections.actionAddFragmentToCarItemFragment(ScreenAddMode.ADD, -1))
                            }
                        })
                )
            }

        }
    }

    private fun setupClickListener() {
        carsListAdapter.onCarItemClickListener = {
            findNavController().navigate(AddFragmentDirections.actionAddFragmentToCarItemFragment(ScreenAddMode.EDIT, it.id))
        }
    }

    private fun setupOnLongClickListener() {
        carsListAdapter.onCarItemLongClickListener = {
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}