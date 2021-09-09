package tennisi.borzot.strada

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import tennisi.borzot.strada.databinding.ActivityMainBinding
import tennisi.borzot.strada.fragments.news.Navigator
import tennisi.borzot.strada.fragments.news.UserDetailsFragment
import tennisi.borzot.strada.fragments.news.model.User
import tennisi.borzot.strada.pager.PagerViewAdapter

private lateinit var mViewPager: ViewPager

private lateinit var addBtn: ImageView
private lateinit var equBtn: ImageView
private lateinit var speedBtn: ImageView
private lateinit var playerBtn: ImageView
private lateinit var setBtn: ImageView
private lateinit var mPagerAdapter: PagerViewAdapter

private lateinit var binding: ActivityMainBinding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // init views
        mViewPager = findViewById(R.id.m_view_pager)

        // init image buttons
        addBtn = findViewById(R.id.add_btn)
        equBtn = findViewById(R.id.equ_btn)
        speedBtn = findViewById(R.id.speed_btn)
        playerBtn = findViewById(R.id.player_btn)
        setBtn = findViewById(R.id.set_btn)

        mPagerAdapter = PagerViewAdapter(supportFragmentManager)
        mViewPager.adapter = mPagerAdapter
        mViewPager.offscreenPageLimit = 5

        // add page change listener
        mViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                changingTabs(position)
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })

        binding.layoutAddCar.isClickable = true
        binding.layoutAddCar.setOnClickListener {
            mViewPager.setCurrentItem(0, true)
        }


        binding.layoutEqualizer.isClickable = true
        binding.layoutEqualizer.setOnClickListener {
            mViewPager.setCurrentItem(1, true)
        }

        binding.layoutSpeed.isClickable = true
        binding.layoutSpeed.setOnClickListener {
            mViewPager.setCurrentItem(2, true)
        }

        binding.layoutPlayer.isClickable = true
        binding.layoutPlayer.setOnClickListener {
            mViewPager.setCurrentItem(3, true)
        }


        binding.layoutSettings.isClickable = true
        binding.layoutSettings.setOnClickListener {
            mViewPager.setCurrentItem(4, true)
        }


        //default tab
        mViewPager.currentItem = 2
        speedBtn.setImageResource(R.drawable.ic_baseline_speed_pink)

    }


    private fun changingTabs(position: Int) {
        if (position == 0) {
            addBtn.setImageResource(R.drawable.ic_baseline_add_pink)
            equBtn.setImageResource(R.drawable.ic_baseline_equalizer_black)
            speedBtn.setImageResource(R.drawable.ic_baseline_speed_black)
            playerBtn.setImageResource(R.drawable.ic_baseline_rss_feed_24)
            setBtn.setImageResource(R.drawable.ic_baseline_settings_black)
        }
        if (position == 1) {
            addBtn.setImageResource(R.drawable.ic_baseline_add_black)
            equBtn.setImageResource(R.drawable.ic_baseline_equalizer_pink)
            speedBtn.setImageResource(R.drawable.ic_baseline_speed_black)
            playerBtn.setImageResource(R.drawable.ic_baseline_rss_feed_24)
            setBtn.setImageResource(R.drawable.ic_baseline_settings_black)
        }
        if (position == 2) {
            addBtn.setImageResource(R.drawable.ic_baseline_add_black)
            equBtn.setImageResource(R.drawable.ic_baseline_equalizer_black)
            speedBtn.setImageResource(R.drawable.ic_baseline_speed_pink)
            playerBtn.setImageResource(R.drawable.ic_baseline_rss_feed_24)
            setBtn.setImageResource(R.drawable.ic_baseline_settings_black)
        }
        if (position == 3) {
            addBtn.setImageResource(R.drawable.ic_baseline_add_black)
            equBtn.setImageResource(R.drawable.ic_baseline_equalizer_black)
            speedBtn.setImageResource(R.drawable.ic_baseline_speed_black)
            playerBtn.setImageResource(R.drawable.ic_baseline_rss_feed_blue)
            setBtn.setImageResource(R.drawable.ic_baseline_settings_black)
        }
        if (position == 4) {
            addBtn.setImageResource(R.drawable.ic_baseline_add_black)
            equBtn.setImageResource(R.drawable.ic_baseline_equalizer_black)
            speedBtn.setImageResource(R.drawable.ic_baseline_speed_black)
            playerBtn.setImageResource(R.drawable.ic_baseline_rss_feed_24)
            setBtn.setImageResource(R.drawable.ic_baseline_settings_pink)
        }
    }
}