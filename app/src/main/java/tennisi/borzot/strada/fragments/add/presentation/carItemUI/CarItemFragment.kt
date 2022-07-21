package tennisi.borzot.strada.fragments.add.presentation.carItemUI

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.net.toUri
import androidx.core.view.drawToBitmap
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import tennisi.borzot.strada.R
import tennisi.borzot.strada.StradaApplication
import tennisi.borzot.strada.databinding.FragmentCarItemBinding
import tennisi.borzot.strada.fragments.add.presentation.ViewModelFactory
import tennisi.borzot.strada.utils.InternalStorageSave
import tennisi.borzot.strada.utils.setImage
import tennisi.borzot.strada.utils.splitDataUniqueId
import javax.inject.Inject


class CarItemFragment : Fragment() {

    private var _binding: FragmentCarItemBinding? = null
    private val binding: FragmentCarItemBinding
        get() = _binding ?: throw RuntimeException("FragmentCarItemBinding == null")

    private val args by navArgs<CarItemFragmentArgs>()

    private lateinit var galleryLauncher: ActivityResultLauncher<Intent>

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[(CarItemViewModel::class.java)]
    }

    private val component by lazy {
        (requireActivity().application as StradaApplication).component
    }

    private val storagePermissionRequestLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions(),
        ::onGotPermissionsForStorage
    )

    private lateinit var onSaveButtonClickListener: OnSaveButtonClickListener
    private lateinit var onItemSelectedListener: OnSaveButtonClickListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnSaveButtonClickListener) {
            onSaveButtonClickListener = context
            onItemSelectedListener = context

        } else {
            throw RuntimeException("Activity must implement OnItemSelectedListener")
        }
        onItemSelectedListener.onItemSelected()
    }

    override fun onDetach() {
        super.onDetach()
        onSaveButtonClickListener.onSaveButtonClick()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCarItemBinding.inflate(inflater, container, false)
        component.inject(this)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        openGallery()
        addChangeTextListeners()
        launchRightMode()
        observeViewModel()
        uploadPhoto()
        setupConfigClickListener()
    }

    private fun uploadPhoto() {
        binding.photoPicker.setOnClickListener {
            storagePermissionRequestLauncher.launch(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE))
        }
    }

    private fun openGallery(){
        galleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result->
            if (result.resultCode == Activity.RESULT_OK && result.data != null) {
                imageUri = result.data?.data.toString()
                binding.imagePicker.setImageURI(imageUri.toUri())
            }
        }
    }



    private fun onGotPermissionsForStorage(grantResults: Map<String, Boolean>) {
        if (grantResults.entries.all { it.value }) {
            Toast.makeText(context, getString(R.string.permission_granted), Toast.LENGTH_SHORT).show()
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                galleryLauncher.launch(gallery)
        } else {
            if (!shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                askUserForOpeningAppSettings()
            } else {
                Toast.makeText(context, getString(R.string.permission_denied), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun askUserForOpeningAppSettings() {
        val appSettingsIntent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts(getString(R.string.package_value), requireActivity().packageName, null)
        )
        if (requireActivity().packageManager.resolveActivity(appSettingsIntent, PackageManager.MATCH_DEFAULT_ONLY) == null) {
            Toast.makeText(context, getString(R.string.forever_denied), Toast.LENGTH_SHORT).show()
        } else {
            AlertDialog.Builder(requireActivity())
                .setTitle(getString(R.string.permission_denied))
                .setMessage(getString(R.string.warning_permission_denied_dialog))
                .setPositiveButton(getString(R.string.open_app_settings)) { _, _ ->
                    startActivity(appSettingsIntent)
                }
                .create()
                .show()
        }
    }


    private fun observeViewModel() {
        viewModel.errorInputProfile.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.invalid_profile)
            } else {
                null
            }
            binding.profileTextField.error = message
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

    private fun setupConfigClickListener() {

        binding.configButton.setOnClickListener {
            findNavController().navigate(CarItemFragmentDirections.actionCarItemFragmentToSoundFragment())
        }


    }

    private fun launchRightMode() {
        when (args.mode.toString()) {
            MODE_EDIT -> launchEditMode()
            MODE_ADD -> launchAddMode()
        }
    }

    private fun addChangeTextListeners() {
        binding.editProfileField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputProfile()
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
            titleForAddCar.text = getText(R.string.edit_current_car)
            viewModel.carItem.observe(viewLifecycleOwner) {
                editBrandField.setText(it.brand)
                editModelField.setText(it.model)
                editProfileField.setText(it.profile)
                checkBoxEnable.isChecked = it.enable
                imagePicker.setImage(requireContext(), it.pathToPic)

            }
            saveButton.setOnClickListener {
                viewModel.editCarItem(
                    editBrandField.text?.toString(),
                    editModelField.text?.toString(),
                    editProfileField.text?.toString(),
                    imageUri = imageUri.split("/").last(),
                    checkBoxEnable.isChecked
                )
                InternalStorageSave.saveToInternalStorage(binding.imagePicker.drawToBitmap(Bitmap.Config.ARGB_8888), imageUri.splitDataUniqueId())
                if (checkBoxEnable.isChecked) viewModel.resetEnableFromCar(args.id)
            }
        }
    }

    private fun launchAddMode() {
        with(binding) {
            titleForAddCar.text = getText(R.string.add_new_car)
            saveButton.setOnClickListener {
                viewModel.addCarItem(
                    editBrandField.text?.toString(),
                    editModelField.text?.toString(),
                    editProfileField.text?.toString(),
                    imageUri = imageUri.split("/").last(),
                    checkBoxEnable.isChecked
                )
                InternalStorageSave.saveToInternalStorage(binding.imagePicker.drawToBitmap(Bitmap.Config.ARGB_8888), imageUri.splitDataUniqueId())
                if (checkBoxEnable.isChecked) viewModel.resetEnableFromCarWithAddNew()
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface OnSaveButtonClickListener {

        fun onSaveButtonClick()

        fun onItemSelected()

    }

    companion object {
        const val MODE_EDIT = "EDIT"
        const val MODE_ADD = "ADD"
        private var imageUri = ""
    }
}