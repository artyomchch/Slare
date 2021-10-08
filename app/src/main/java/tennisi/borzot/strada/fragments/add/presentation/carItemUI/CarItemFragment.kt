package tennisi.borzot.strada.fragments.add.presentation.carItemUI

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import tennisi.borzot.strada.R
import tennisi.borzot.strada.databinding.FragmentCarItemBinding
import tennisi.borzot.strada.fragments.add.domain.CarItem

class CarItemFragment : Fragment() {

    private var _binding: FragmentCarItemBinding? = null
    private val binding: FragmentCarItemBinding
        get() = _binding ?: throw RuntimeException("FragmentCarItemBinding == null")

    private lateinit var viewModel: CarItemViewModel
    private var screenMode: String = MODE_UNKNOWN
    private var carItemId: Int = CarItem.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCarItemBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolBar()
        viewModel = ViewModelProvider(this)[CarItemViewModel::class.java]
        addChangeTextListeners()
        launchRightMode()
        observeViewModel()
    }


    private fun observeViewModel() {
        viewModel.errorInputName.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.invalid_name)
            } else {
                null
            }
            binding.nameTextField.error = message
        }

        viewModel.errorInputBrand.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.invalid_brand)
            } else {
                null
            }
            binding.brandTextField.error = message
        }

        viewModel.errorInputModel.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.invalid_model)
            } else {
                null
            }
            binding.modelTextField.error = message
        }

        viewModel.shouldCloseScreen.observe(viewLifecycleOwner) {
            activity?.onBackPressed()
        }
    }

    private fun launchRightMode() {
        when (screenMode) {
            MODE_EDIT -> launchEditMode()
            MODE_ADD -> launchAddMode()
        }
    }

    private fun addChangeTextListeners() {
        binding.editNameField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputName()
            }

            override fun afterTextChanged(p0: Editable?) {}

        })

        binding.editBrandField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputBrand()
            }

            override fun afterTextChanged(p0: Editable?) {}

        })

        binding.editModelField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputModel()
            }

            override fun afterTextChanged(p0: Editable?) {}

        })
    }

    private fun launchEditMode() {
        with(binding) {
            addChangeCarToolbar.toolbarText.text = getString(R.string.edit_vehicle)
            viewModel.getCarItem(carItemId)
            viewModel.carItem.observe(viewLifecycleOwner) {

                editNameField.setText(it.name)
                editBrandField.setText(it.brand)
                editModelField.setText(it.model)

            }
            saveButton.setOnClickListener {
                viewModel.editCarItem(editNameField.text?.toString(), editBrandField.text?.toString(), editModelField.text?.toString())
            }
        }


    }

    private fun launchAddMode() {
        with(binding) {
            addChangeCarToolbar.toolbarText.text = getString(R.string.add_vehicle)
            saveButton.setOnClickListener {
                viewModel.addCarItem(editNameField.text?.toString(), editBrandField.text?.toString(), editModelField.text?.toString())
            }
        }


    }

    private fun parseParams() {
        val args = requireArguments()
        if (args.containsKey(SCREEN_MODE)) {
            throw RuntimeException("Param screen mode if absent")
        }
        val mode = args.getString(SCREEN_MODE)
        if (mode != MODE_EDIT && mode != MODE_ADD) {
            throw RuntimeException("Unknown screen mode $mode")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!args.containsKey(CAR_ITEM_ID)) {
                throw RuntimeException("Param shop item id id absent")
            }
            carItemId = args.getInt(CAR_ITEM_ID, CarItem.UNDEFINED_ID)
        }
    }

    private fun setToolBar() {
        with(binding) {
            addChangeCarToolbar.toolbarImageSignIn.visibility = View.GONE
            addChangeCarToolbar.toolbarImageItem.apply {
                setImageResource(R.drawable.ic_arrow_back)
                background = with(TypedValue()) {
                    context.theme.resolveAttribute(
                        R.attr.selectableItemBackground, this, true
                    )
                    ContextCompat.getDrawable(context, resourceId)
                }
                setOnClickListener {
                    activity?.onBackPressed()
                }
            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        private const val SCREEN_MODE = "extra_mode"
        private const val CAR_ITEM_ID = "extra_item"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode add"
        private const val MODE_UNKNOWN = ""

        fun newInstanceAddItem(): CarItemFragment {
            return CarItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_ADD)
                }
            }
        }

        fun newInstanceEditItem(carItemId: Int): CarItemFragment {
            return CarItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_EDIT)
                    putInt(CAR_ITEM_ID, carItemId)
                }
            }
        }

    }
}