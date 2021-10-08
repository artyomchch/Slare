package tennisi.borzot.strada.fragments.add.presentation.carItemUI

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tennisi.borzot.strada.R
import tennisi.borzot.strada.databinding.ActivityCarItemBinding
import tennisi.borzot.strada.fragments.add.domain.CarItem

class CarItemActivity : AppCompatActivity() {

    private val binding: ActivityCarItemBinding by lazy { ActivityCarItemBinding.inflate(layoutInflater) }

    ///  private lateinit var viewModel: CarItemViewModel
    private var screenMode = MODE_UNKNOWN
    private var carItemId = CarItem.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        parseIntent()
        //   setToolBar()
        //viewModel = ViewModelProvider(this)[CarItemViewModel::class.java]
        //     addChangeTextListeners()
        launchRightMode()
        //    observeViewModel()
    }

    //    private fun observeViewModel() {
//        viewModel.errorInputName.observe(this) {
//            val message = if (it) {
//                getString(R.string.invalid_name)
//            } else {
//                null
//            }
//            binding.nameTextField.error = message
//        }
//
//        viewModel.errorInputBrand.observe(this) {
//            val message = if (it) {
//                getString(R.string.invalid_brand)
//            } else {
//                null
//            }
//            binding.brandTextField.error = message
//        }
//
//        viewModel.errorInputModel.observe(this) {
//            val message = if (it) {
//                getString(R.string.invalid_model)
//            } else {
//                null
//            }
//            binding.modelTextField.error = message
//        }
//
//        viewModel.shouldCloseScreen.observe(this) {
//            finish()
//        }
//    }
//
    private fun launchRightMode() {
        val fragment = when (screenMode) {
            MODE_EDIT -> CarItemFragment.newInstanceEditItem(carItemId)
            MODE_ADD -> CarItemFragment.newInstanceAddItem()
            else -> throw RuntimeException("Unknown screen mode $screenMode")
        }

        supportFragmentManager.beginTransaction()
            .add(R.id.car_item_container, fragment)
            .commit()
    }

    //
//    private fun addChangeTextListeners() {
//        binding.editNameField.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                viewModel.resetErrorInputName()
//            }
//
//            override fun afterTextChanged(p0: Editable?) {}
//
//        })
//
//        binding.editBrandField.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                viewModel.resetErrorInputBrand()
//            }
//
//            override fun afterTextChanged(p0: Editable?) {}
//
//        })
//
//        binding.editModelField.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                viewModel.resetErrorInputModel()
//            }
//
//            override fun afterTextChanged(p0: Editable?) {}
//
//        })
//    }
//
//    private fun launchEditMode() {
//        with(binding) {
//            addChangeCarToolbar.toolbarText.text = getString(R.string.edit_vehicle)
//            viewModel.getCarItem(carItemId)
//            viewModel.carItem.observe(this@CarItemActivity) {
//
//                editNameField.setText(it.name)
//                editBrandField.setText(it.brand)
//                editModelField.setText(it.model)
//
//            }
//            saveButton.setOnClickListener {
//                viewModel.editCarItem(editNameField.text?.toString(), editBrandField.text?.toString(), editModelField.text?.toString())
//            }
//        }
//
//
//    }
//
//    private fun launchAddMode() {
//        with(binding) {
//            addChangeCarToolbar.toolbarText.text = getString(R.string.add_vehicle)
//            saveButton.setOnClickListener {
//                viewModel.addCarItem(editNameField.text?.toString(), editBrandField.text?.toString(), editModelField.text?.toString())
//            }
//        }
//
//
//    }
//
    private fun parseIntent() {
        if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
            throw RuntimeException("Param screen mode is absent")
        }
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        if (mode != MODE_EDIT && mode != MODE_ADD) {
            throw RuntimeException("Unknown screen mode $mode")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!intent.hasExtra(EXTRA_CAR_ITEM_ID)) {
                throw RuntimeException("Param shop item id is absent")
            }
            carItemId = intent.getIntExtra(EXTRA_CAR_ITEM_ID, CarItem.UNDEFINED_ID)
        }
    }
//
//    private fun setToolBar() {
//        with(binding) {
//            addChangeCarToolbar.toolbarImageSignIn.visibility = View.GONE
//            addChangeCarToolbar.toolbarImageItem.apply {
//                setImageResource(R.drawable.ic_arrow_back)
//                background = with(TypedValue()) {
//                    context.theme.resolveAttribute(
//                        R.attr.selectableItemBackground, this, true
//                    )
//                    ContextCompat.getDrawable(context, resourceId)
//                }
//                setOnClickListener {
//                    finish()
//                }
//            }
//        }
//
//    }

    companion object {

        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_CAR_ITEM_ID = "extra_item"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val MODE_UNKNOWN = ""

        fun newIntentAddItem(context: Context): Intent {
            val intent = Intent(context, CarItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEditItem(context: Context, carItemId: Int): Intent {
            val intent = Intent(context, CarItemActivity::class.java)
            intent.apply {
                putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
                putExtra(EXTRA_CAR_ITEM_ID, carItemId)
            }
            return intent
        }

    }

}