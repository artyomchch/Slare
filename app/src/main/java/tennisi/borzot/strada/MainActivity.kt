package tennisi.borzot.strada

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import tennisi.borzot.strada.databinding.ActivityMainBinding
import tennisi.borzot.strada.pager.PagerViewAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var mPagerAdapter: PagerViewAdapter
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        mPagerAdapter = PagerViewAdapter(supportFragmentManager)
        binding.mViewPager.adapter = mPagerAdapter
        binding.mViewPager.offscreenPageLimit = 5

        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser

        Glide.with(this)
            .load(currentUser?.photoUrl)
            .circleCrop()
            .into(binding.mainFragmentToolbar.toolbarImageSignIn)

        // add page change listener
        binding.mViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {}

            override fun onPageSelected(position: Int) {
                changingTabs(position)
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })

        binding.layoutAddCar.isClickable = true
        binding.layoutAddCar.setOnClickListener {
            binding.mViewPager.setCurrentItem(0, true)
        }


        binding.layoutEqualizer.isClickable = true
        binding.layoutEqualizer.setOnClickListener {
            binding.mViewPager.setCurrentItem(1, true)
        }

        binding.layoutSpeed.isClickable = true
        binding.layoutSpeed.setOnClickListener {
            binding.mViewPager.setCurrentItem(2, true)
        }

        binding.layoutPlayer.isClickable = true
        binding.layoutPlayer.setOnClickListener {
            binding.mViewPager.setCurrentItem(3, true)
        }


        binding.layoutSettings.isClickable = true
        binding.layoutSettings.setOnClickListener {
            binding.mViewPager.setCurrentItem(4, true)
        }


        //default tab
        binding.mViewPager.currentItem = 2
        binding.speedBtn.setImageResource(R.drawable.ic_baseline_speed_purple)

    }


    private fun changingTabs(position: Int) {
        if (position == 0) {
            binding.mainFragmentToolbar.toolbarText.text = getString(R.string.vehicle)
            binding.mainFragmentToolbar.toolbarImageItem.setImageResource(R.drawable.ic_baseline_add_purple)
            binding.addBtn.setImageResource(R.drawable.ic_baseline_add_purple)
            binding.equBtn.setImageResource(R.drawable.ic_baseline_equalizer_black)
            binding.speedBtn.setImageResource(R.drawable.ic_baseline_speed_black)
            binding.newsBtn.setImageResource(R.drawable.ic_baseline_rss_feed_24)
            binding.setBtn.setImageResource(R.drawable.ic_baseline_settings_black)
        }
        if (position == 1) {
            binding.mainFragmentToolbar.toolbarText.text = getString(R.string.equalizer)
            binding.mainFragmentToolbar.toolbarImageItem.setImageResource(R.drawable.ic_baseline_equalizer_purple)
            binding.addBtn.setImageResource(R.drawable.ic_baseline_add_black)
            binding.equBtn.setImageResource(R.drawable.ic_baseline_equalizer_purple)
            binding.speedBtn.setImageResource(R.drawable.ic_baseline_speed_black)
            binding.newsBtn.setImageResource(R.drawable.ic_baseline_rss_feed_24)
            binding.setBtn.setImageResource(R.drawable.ic_baseline_settings_black)
        }
        if (position == 2) {
            binding.mainFragmentToolbar.toolbarText.text = getString(R.string.speed)
            binding.mainFragmentToolbar.toolbarImageItem.setImageResource(R.drawable.ic_baseline_speed_purple)
            binding.addBtn.setImageResource(R.drawable.ic_baseline_add_black)
            binding.equBtn.setImageResource(R.drawable.ic_baseline_equalizer_black)
            binding.speedBtn.setImageResource(R.drawable.ic_baseline_speed_purple)
            binding.newsBtn.setImageResource(R.drawable.ic_baseline_rss_feed_24)
            binding.setBtn.setImageResource(R.drawable.ic_baseline_settings_black)
        }
        if (position == 3) {
            binding.mainFragmentToolbar.toolbarText.text = getString(R.string.news)
            binding.mainFragmentToolbar.toolbarImageItem.setImageResource(R.drawable.ic_baseline_rss_feed_purple)
            binding.addBtn.setImageResource(R.drawable.ic_baseline_add_black)
            binding.equBtn.setImageResource(R.drawable.ic_baseline_equalizer_black)
            binding.speedBtn.setImageResource(R.drawable.ic_baseline_speed_black)
            binding.newsBtn.setImageResource(R.drawable.ic_baseline_rss_feed_purple)
            binding.setBtn.setImageResource(R.drawable.ic_baseline_settings_black)
        }
        if (position == 4) {
            binding.mainFragmentToolbar.toolbarText.text = getString(R.string.settings)
            binding.mainFragmentToolbar.toolbarImageItem.setImageResource(R.drawable.ic_baseline_settings_purple)
            binding.addBtn.setImageResource(R.drawable.ic_baseline_add_black)
            binding.equBtn.setImageResource(R.drawable.ic_baseline_equalizer_black)
            binding.speedBtn.setImageResource(R.drawable.ic_baseline_speed_black)
            binding.newsBtn.setImageResource(R.drawable.ic_baseline_rss_feed_24)
            binding.setBtn.setImageResource(R.drawable.ic_baseline_settings_purple)
        }
    }
}