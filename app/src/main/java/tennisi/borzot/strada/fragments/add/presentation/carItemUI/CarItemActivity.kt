package tennisi.borzot.strada.fragments.add.presentation.carItemUI

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import tennisi.borzot.strada.R
import tennisi.borzot.strada.databinding.ActivityCarItemBinding

class CarItemActivity : AppCompatActivity() {

    private val binding: ActivityCarItemBinding by lazy { ActivityCarItemBinding.inflate(layoutInflater) }
    private lateinit var viewModel: CarItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[CarItemViewModel::class.java]

        if (intent.getStringExtra(EXTRA_SCREEN_MODE) == MODE_ADD) {
            binding.addChangeCarToolbar.toolbarText.text = getString(R.string.add_vehicle)
        } else
            binding.addChangeCarToolbar.toolbarText.text = getString(R.string.edit_vehicle)
        binding.addChangeCarToolbar.toolbarImageSignIn.visibility = View.GONE
        binding.addChangeCarToolbar.toolbarImageItem.apply {
            setImageResource(R.drawable.ic_arrow_back)
            background = with(TypedValue()) {
                context.theme.resolveAttribute(
                    R.attr.selectableItemBackground, this, true
                )
                ContextCompat.getDrawable(context, resourceId)
            }
            setOnClickListener {
                finish()
            }
        }
    }

    companion object {

        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode add"

        fun newIntentAddItem(context: Context): Intent {
            val intent = Intent(context, CarItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEditItem(context: Context, carItemId: Int): Intent {
            val intent = Intent(context, CarItemActivity::class.java)
            intent.apply {
                putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
                putExtra(EXTRA_SCREEN_MODE, carItemId)
            }
            return intent
        }

    }

}