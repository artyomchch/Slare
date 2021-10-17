package tennisi.borzot.strada.fragments.add.presentation.carItemUI

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import tennisi.borzot.strada.R
import tennisi.borzot.strada.databinding.FragmentCarItemBinding


class CarItemFragment : Fragment() {

    private var _binding: FragmentCarItemBinding? = null
    private val binding: FragmentCarItemBinding
        get() = _binding ?: throw RuntimeException("FragmentCarItemBinding == null")

    private val args by navArgs<CarItemFragmentArgs>()

    private lateinit var viewModel: CarItemViewModel
    private lateinit var onSaveButtonClickListener: OnSaveButtonClickListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnSaveButtonClickListener) {
            onSaveButtonClickListener = context
        } else {
            throw RuntimeException("Activity must implement OnItemSelectedListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        onSaveButtonClickListener.onSaveButtonClick()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCarItemBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        when (args.mode.toString()) {
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
            viewModel.getCarItem(args.id)
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
            saveButton.setOnClickListener {
                viewModel.addCarItem(editNameField.text?.toString(), editBrandField.text?.toString(), editModelField.text?.toString())
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface OnSaveButtonClickListener {

        fun onSaveButtonClick()
    }

    companion object {
        const val MODE_EDIT = "EDIT"
        const val MODE_ADD = "ADD"
    }
}