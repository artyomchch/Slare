package tennisi.borzot.strada.homeScreen.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import tennisi.borzot.strada.R
import tennisi.borzot.strada.databinding.ActivityMainBinding
import tennisi.borzot.strada.fragments.add.presentation.addFragmentUI.AddFragment
import tennisi.borzot.strada.fragments.add.presentation.carItemUI.CarItemFragment
import tennisi.borzot.strada.fragments.equalizer.EqualizerFragment
import tennisi.borzot.strada.fragments.news.presentation.NewsFragment
import tennisi.borzot.strada.fragments.settings.SettingsFragment
import tennisi.borzot.strada.fragments.speed.SpeedFragment
import tennisi.borzot.strada.utils.KeyboardUtils

class MainActivity : AppCompatActivity(), AddFragment.OnItemSelectedListener, CarItemFragment.OnSaveButtonClickListener {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val addFragment by lazy { AddFragment() }
    private val equalizerFragment by lazy { EqualizerFragment() }
    private val speedFragment by lazy { SpeedFragment() }
    private val newsFragment by lazy { NewsFragment() }
    private val settingsFragment by lazy { SettingsFragment() }
    private lateinit var viewModel: MainActivityViewModel


    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        observeViewModel()
        authentication()

        replaceFragment(setFragment(viewModel.currentNameFragment.value.toString()))
        //changingTabs()
        selectedListener()
        //   binding.bottomNavigationMenu.menu.findItem(R.id.speedFragment).isChecked = true
        //   setResource(getString(R.string.speed), R.drawable.ic_baseline_speed_purple)
    }


    private fun selectedListener() {
        binding.bottomNavigationMenu.setOnItemSelectedListener {
            changingTabs(it.itemId)
            true
        }
    }

    private fun observeViewModel() {
        viewModel.currentNameFragment.observe(this) {
            Log.d("logFragment", "observeViewModel: $it ")
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

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.apply {
            beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
        }
    }

    private fun setFragment(fragmentName: String): Fragment {
        return when (fragmentName) {
            MODE_ADD -> addFragment
            MODE_EQUALIZER -> equalizerFragment
            MODE_SPEED -> speedFragment
            MODE_NEWS -> newsFragment
            MODE_SETTINGS -> settingsFragment
            else -> speedFragment
        }


    }

    private fun changingTabs(idFragment: Int) {

        if (idFragment == R.id.addFragment) {
            viewModel.setCurrentNameFragment(MODE_ADD)
            setResource(getString(R.string.vehicle), R.drawable.ic_baseline_add_purple)
            replaceFragment(addFragment)
        }
        if (idFragment == R.id.equalizerFragment) {
            viewModel.setCurrentNameFragment(MODE_EQUALIZER)
            setResource(getString(R.string.equalizer), R.drawable.ic_baseline_equalizer_purple)
            replaceFragment(equalizerFragment)
        }
        if (idFragment == R.id.speedFragment) {
            viewModel.setCurrentNameFragment(MODE_SPEED)
            setResource(getString(R.string.speed), R.drawable.ic_baseline_speed_purple)
            replaceFragment(speedFragment)
        }
        if (idFragment == R.id.newsFragment) {
            viewModel.setCurrentNameFragment(MODE_NEWS)
            setResource(getString(R.string.news), R.drawable.ic_baseline_rss_feed_purple)
            replaceFragment(newsFragment)
        }
        if (idFragment == R.id.settingsFragment) {
            viewModel.setCurrentNameFragment(MODE_SETTINGS)
            setResource(getString(R.string.settings), R.drawable.ic_baseline_settings_purple)
            replaceFragment(settingsFragment)
        }
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
        private const val MODE_ADD = "mode_add"
        private const val MODE_EQUALIZER = "mode_equalizer"
        private const val MODE_SPEED = "mode_speed"
        private const val MODE_NEWS = "mode_news"
        private const val MODE_SETTINGS = "mode_settings"
    }


}