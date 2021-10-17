package tennisi.borzot.strada.homeScreen.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import tennisi.borzot.strada.R
import tennisi.borzot.strada.databinding.ActivityMainBinding
import tennisi.borzot.strada.fragments.add.presentation.addFragmentUI.AddFragment
import tennisi.borzot.strada.fragments.add.presentation.carItemUI.CarItemFragment
import tennisi.borzot.strada.utils.KeyboardUtils

class MainActivity : AppCompatActivity(), AddFragment.OnItemSelectedListener, CarItemFragment.OnSaveButtonClickListener {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var viewModel: MainActivityViewModel

    private lateinit var navController: NavController
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navHost = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHost.navController
        binding.bottomNavigationMenu.setupWithNavController(navController)

        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        bottomNavigationViewListening()
        observeViewModel()
        authentication()

    }

    private fun observeViewModel() {
        viewModel.currentNameFragment.observe(this) {
            Log.d("logFragment", "observeViewModel: $it ")
        }
    }

    private fun bottomNavigationViewListening() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            changingTabs(destination.label.toString())
        }
    }


    private fun authentication() {
        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser

        Glide.with(this)
            .load(currentUser?.photoUrl)
            .circleCrop()
            .placeholder(R.drawable.ic_user_avatar)
            .into(binding.mainFragmentToolbar.toolbarImageSignIn)
    }


    private fun changingTabs(nameFragment: String) {
        if (nameFragment == FRAGMENT_ADD)
            setResource(getString(R.string.vehicle), R.drawable.ic_baseline_add_purple)
        if (nameFragment == FRAGMENT_EQUALIZER)
            setResource(getString(R.string.equalizer), R.drawable.ic_baseline_equalizer_purple)
        if (nameFragment == FRAGMENT_SPEED)
            setResource(getString(R.string.speed), R.drawable.ic_baseline_speed_purple)
        if (nameFragment == FRAGMENT_NEWS)
            setResource(getString(R.string.news), R.drawable.ic_baseline_rss_feed_purple)
        if (nameFragment == FRAGMENT_SETTINGS)
            setResource(getString(R.string.settings), R.drawable.ic_baseline_settings_purple)

    }

    private fun setResource(title: String, imageResource: Int) {
        with(binding) {
            mainFragmentToolbar.apply {
                toolbarText.text = title
                toolbarImageItem.setImageResource(imageResource)
            }
        }
    }

    private fun hideResource() {
        with(binding) {
            mainFragmentToolbar.linearLayoutToolBar.visibility = View.GONE
            bottomNavigationMenu.visibility = View.GONE
        }
    }

    private fun showResource() {
        with(binding) {
            mainFragmentToolbar.linearLayoutToolBar.visibility = View.VISIBLE
            bottomNavigationMenu.visibility = View.VISIBLE
        }
        KeyboardUtils.hideKeyboard(this)
    }


    override fun onItemSelected() {
        hideResource()
    }

    override fun onSaveButtonClick() {
        showResource()
    }

    companion object {
        private const val FRAGMENT_ADD = "fragment_add"
        private const val FRAGMENT_EQUALIZER = "fragment_equalizer"
        private const val FRAGMENT_SPEED = "fragment_speed"
        private const val FRAGMENT_NEWS = "fragment_news"
        private const val FRAGMENT_SETTINGS = "fragment_settings"
    }

}