package tennisi.borzot.strada

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import tennisi.borzot.strada.databinding.ActivityMainBinding
import tennisi.borzot.strada.fragments.add.presentation.addFragmentUI.AddFragment
import tennisi.borzot.strada.fragments.equalizer.EqualizerFragment
import tennisi.borzot.strada.fragments.news.presentation.NewsFragment
import tennisi.borzot.strada.fragments.settings.SettingsFragment
import tennisi.borzot.strada.fragments.speed.SpeedFragment

class MainActivity : AppCompatActivity() {

    //   private lateinit var mScreenSlidePagerAdapter: ScreenSlidePagerAdapter
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
        replaceFragment(speedFragment)
        binding.bottomNavigationMenu.menu.findItem(R.id.speedFragment).isChecked = true
        binding.mainFragmentToolbar.toolbarText.text = getString(R.string.speed)
        binding.mainFragmentToolbar.toolbarImageItem.setImageResource(R.drawable.ic_baseline_speed_purple)

        binding.bottomNavigationMenu.setOnItemSelectedListener {
            changingTabs(it.itemId)
            true
        }



        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser

        Glide.with(this)
            .load(currentUser?.photoUrl)
            .circleCrop()
            .into(binding.mainFragmentToolbar.toolbarImageSignIn)


        //var adapter = ScreenSlidePagerAdapter(supportFragmentManager, lifecycle)

        //mPagerAdapter = PagerViewAdapter(supportFragmentManager)

        //mScreenSlidePagerAdapter = ScreenSlidePagerAdapter(this)
        //binding.mViewPager.adapter = mScreenSlidePagerAdapter
        //  binding.mViewPager.offscreenPageLimit = 5



        // add page change listener
//        binding.mViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                super.onPageSelected(position)
//                changingTabs(position)
//            }
//
//
//        })


//        binding.layoutAddCar.setOnClickListener {
//            binding.mViewPager.setCurrentItem(0, true)
//        }
//
//
//
//        binding.layoutEqualizer.setOnClickListener {
//            binding.mViewPager.setCurrentItem(1, true)
//        }
//
//
//        binding.layoutSpeed.setOnClickListener {
//            binding.mViewPager.setCurrentItem(2, true)
//        }
//
//
//        binding.layoutPlayer.setOnClickListener {
//            binding.mViewPager.setCurrentItem(3, true)
//        }
//
//
//
//        binding.layoutSettings.setOnClickListener {
//            binding.mViewPager.setCurrentItem(4, true)
//        }
//
//
//        //default tab
//   //     binding.mViewPager.currentItem = 2
//        binding.speedBtn.setImageResource(R.drawable.ic_baseline_speed_purple)
//

    }


        //binding.bottomNavigationMenu.setupWithNavController(binding.navigationHost.findNavController())

//        binding.bottomNavigationMenu.setOnItemSelectedListener {
//
//            changingTabs(it.itemId)
//            true
//        }



    private fun replaceFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }


    private fun changingTabs(position: Int) {
        if (position == R.id.addFragment) {
            binding.mainFragmentToolbar.toolbarText.text = getString(R.string.vehicle)
            binding.mainFragmentToolbar.toolbarImageItem.setImageResource(R.drawable.ic_baseline_add_purple)
            replaceFragment(addFragment)
//            binding.addBtn.setImageResource(R.drawable.ic_baseline_add_purple)
//            binding.equBtn.setImageResource(R.drawable.ic_baseline_equalizer_black)
//            binding.speedBtn.setImageResource(R.drawable.ic_baseline_speed_black)
//            binding.newsBtn.setImageResource(R.drawable.ic_baseline_rss_feed_24)
//            binding.setBtn.setImageResource(R.drawable.ic_baseline_settings_black)
        }
        if (position == R.id.equalizerFragment) {
            binding.mainFragmentToolbar.toolbarText.text = getString(R.string.equalizer)
            binding.mainFragmentToolbar.toolbarImageItem.setImageResource(R.drawable.ic_baseline_equalizer_purple)
            replaceFragment(equalizerFragment)
//            binding.addBtn.setImageResource(R.drawable.ic_baseline_add_black)
//            binding.equBtn.setImageResource(R.drawable.ic_baseline_equalizer_purple)
//            binding.speedBtn.setImageResource(R.drawable.ic_baseline_speed_black)
//            binding.newsBtn.setImageResource(R.drawable.ic_baseline_rss_feed_24)
//            binding.setBtn.setImageResource(R.drawable.ic_baseline_settings_black)
        }
        if (position == R.id.speedFragment) {
            binding.mainFragmentToolbar.toolbarText.text = getString(R.string.speed)
            binding.mainFragmentToolbar.toolbarImageItem.setImageResource(R.drawable.ic_baseline_speed_purple)
            replaceFragment(speedFragment)
//            binding.addBtn.setImageResource(R.drawable.ic_baseline_add_black)
//            binding.equBtn.setImageResource(R.drawable.ic_baseline_equalizer_black)
//            binding.speedBtn.setImageResource(R.drawable.ic_baseline_speed_purple)
//            binding.newsBtn.setImageResource(R.drawable.ic_baseline_rss_feed_24)
//            binding.setBtn.setImageResource(R.drawable.ic_baseline_settings_black)
        }
        if (position == R.id.newsFragment) {
            binding.mainFragmentToolbar.toolbarText.text = getString(R.string.news)
            binding.mainFragmentToolbar.toolbarImageItem.setImageResource(R.drawable.ic_baseline_rss_feed_purple)
            replaceFragment(newsFragment)
//            binding.addBtn.setImageResource(R.drawable.ic_baseline_add_black)
//            binding.equBtn.setImageResource(R.drawable.ic_baseline_equalizer_black)
//            binding.speedBtn.setImageResource(R.drawable.ic_baseline_speed_black)
//            binding.newsBtn.setImageResource(R.drawable.ic_baseline_rss_feed_purple)
//            binding.setBtn.setImageResource(R.drawable.ic_baseline_settings_black)
        }
        if (position == R.id.settingsFragment) {
            binding.mainFragmentToolbar.toolbarText.text = getString(R.string.settings)
            binding.mainFragmentToolbar.toolbarImageItem.setImageResource(R.drawable.ic_baseline_settings_purple)
            replaceFragment(settingsFragment)
//            binding.addBtn.setImageResource(R.drawable.ic_baseline_add_black)
//            binding.equBtn.setImageResource(R.drawable.ic_baseline_equalizer_black)
//            binding.speedBtn.setImageResource(R.drawable.ic_baseline_speed_black)
//            binding.newsBtn.setImageResource(R.drawable.ic_baseline_rss_feed_24)
//            binding.setBtn.setImageResource(R.drawable.ic_baseline_settings_purple)
        }
    }


}