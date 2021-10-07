package tennisi.borzot.strada

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import tennisi.borzot.strada.databinding.ActivityMainBinding
import tennisi.borzot.strada.fragments.add.presentation.addFragmentUI.AddFragment
import tennisi.borzot.strada.fragments.equalizer.EqualizerFragment
import tennisi.borzot.strada.fragments.news.presentation.NewsFragment
import tennisi.borzot.strada.fragments.settings.SettingsFragment
import tennisi.borzot.strada.fragments.speed.SpeedFragment

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val addFragment = AddFragment()
    private val equalizerFragment = EqualizerFragment()
    private val speedFragment = SpeedFragment()
    private val newsFragment = NewsFragment()
    private val settingsFragment = SettingsFragment()

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        authentication()
        replaceFragment(speedFragment)
        selectedListener()
        binding.bottomNavigationMenu.menu.findItem(R.id.speedFragment).isChecked = true
        setResource(getString(R.string.speed), R.drawable.ic_baseline_speed_purple)
    }


    private fun selectedListener() {
        binding.bottomNavigationMenu.setOnItemSelectedListener {
            changingTabs(it.itemId)
            true
        }
    }

    private fun authentication() {
        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser

        Glide.with(this)
            .load(currentUser?.photoUrl)
            .circleCrop()
            .into(binding.mainFragmentToolbar.toolbarImageSignIn)
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }

    private fun changingTabs(idFragment: Int) {

        if (idFragment == R.id.addFragment) {
            setResource(getString(R.string.vehicle), R.drawable.ic_baseline_add_purple)
            replaceFragment(addFragment)
        }
        if (idFragment == R.id.equalizerFragment) {
            setResource(getString(R.string.equalizer), R.drawable.ic_baseline_equalizer_purple)
            replaceFragment(equalizerFragment)
        }
        if (idFragment == R.id.speedFragment) {
            setResource(getString(R.string.speed), R.drawable.ic_baseline_speed_purple)
            replaceFragment(speedFragment)
        }
        if (idFragment == R.id.newsFragment) {
            setResource(getString(R.string.news), R.drawable.ic_baseline_rss_feed_purple)
            replaceFragment(newsFragment)
        }
        if (idFragment == R.id.settingsFragment) {
            setResource(getString(R.string.settings), R.drawable.ic_baseline_settings_purple)
            replaceFragment(settingsFragment)
        }
    }

    private fun setResource(title: String, imageResource: Int) {
        with(binding) {
            mainFragmentToolbar.toolbarText.text = title
            mainFragmentToolbar.toolbarImageItem.setImageResource(imageResource)
        }
    }


}