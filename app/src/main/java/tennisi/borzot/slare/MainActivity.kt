package tennisi.borzot.slare

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import tennisi.borzot.slare.pager.PagerViewAdapter

private lateinit var mViewPager: ViewPager

private lateinit var addBtn: ImageView
private lateinit var equBtn: ImageView
private lateinit var speedBtn: ImageView
private lateinit var playerBtn: ImageView
private lateinit var setBtn: ImageView
private lateinit var mPagerAdapter: PagerViewAdapter


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hideSystemUI()
        Toast.makeText(this, "Welcome", Toast.LENGTH_LONG).show()

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
        mViewPager.offscreenPageLimit  = 5

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

        layoutAddCar.isClickable = true;
        layoutAddCar.setOnClickListener(
            View.OnClickListener {
                mViewPager.setCurrentItem(0, true)
            }
        )


        layoutEqualizer.isClickable = true;
        layoutEqualizer.setOnClickListener(
            View.OnClickListener {
                mViewPager.setCurrentItem(1, true)
            }
        )

        layoutSpeed.isClickable = true;
        layoutSpeed.setOnClickListener(
            View.OnClickListener {
                mViewPager.setCurrentItem(2, true)
            }
        )

        layoutPlayer.isClickable = true;
        layoutPlayer.setOnClickListener(
            View.OnClickListener {
                mViewPager.setCurrentItem(3, true)
            }
        )


        layoutSettings.isClickable = true;
        layoutSettings.setOnClickListener(
            View.OnClickListener {
                mViewPager.setCurrentItem(4, true)
            }
        )



        //default tab
        mViewPager.currentItem = 2
        speedBtn.setImageResource(R.drawable.ic_baseline_speed_pink)


    }


    private fun setCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.m_view_pager, fragment)
        }
    }

    private fun changingTabs(position: Int){
        if (position == 0){
            addBtn.setImageResource(R.drawable.ic_baseline_add_pink)
            equBtn.setImageResource(R.drawable.ic_baseline_equalizer_black)
            speedBtn.setImageResource(R.drawable.ic_baseline_speed_black)
            playerBtn.setImageResource(R.drawable.ic_baseline_play_circle_filled_black)
            setBtn.setImageResource(R.drawable.ic_baseline_settings_black)
        }
        if (position == 1){
            addBtn.setImageResource(R.drawable.ic_baseline_add_black)
            equBtn.setImageResource(R.drawable.ic_baseline_equalizer_pink)
            speedBtn.setImageResource(R.drawable.ic_baseline_speed_black)
            playerBtn.setImageResource(R.drawable.ic_baseline_play_circle_filled_black)
            setBtn.setImageResource(R.drawable.ic_baseline_settings_black)
        }
        if (position == 2){
            addBtn.setImageResource(R.drawable.ic_baseline_add_black)
            equBtn.setImageResource(R.drawable.ic_baseline_equalizer_black)
            speedBtn.setImageResource(R.drawable.ic_baseline_speed_pink)
            playerBtn.setImageResource(R.drawable.ic_baseline_play_circle_filled_black)
            setBtn.setImageResource(R.drawable.ic_baseline_settings_black)
        }
        if (position == 3){
            addBtn.setImageResource(R.drawable.ic_baseline_add_black)
            equBtn.setImageResource(R.drawable.ic_baseline_equalizer_black)
            speedBtn.setImageResource(R.drawable.ic_baseline_speed_black)
            playerBtn.setImageResource(R.drawable.ic_baseline_play_circle_filled_pink)
            setBtn.setImageResource(R.drawable.ic_baseline_settings_black)
        }
        if (position == 4){
            addBtn.setImageResource(R.drawable.ic_baseline_add_black)
            equBtn.setImageResource(R.drawable.ic_baseline_equalizer_black)
            speedBtn.setImageResource(R.drawable.ic_baseline_speed_black)
            playerBtn.setImageResource(R.drawable.ic_baseline_play_circle_filled_black)
            setBtn.setImageResource(R.drawable.ic_baseline_settings_pink)
        }
    }














    private fun hideSystemUI() {
        val decorView: View = window.decorView
        val uiOptions = decorView.systemUiVisibility
        var newUiOptions = uiOptions
      //  newUiOptions = newUiOptions or View.SYSTEM_UI_FLAG_LOW_PROFILE
     //   newUiOptions = newUiOptions or View.SYSTEM_UI_FLAG_FULLSCREEN
        newUiOptions = newUiOptions or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        newUiOptions = newUiOptions or View.SYSTEM_UI_FLAG_IMMERSIVE
        //newUiOptions = newUiOptions or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
      //  newUiOptions = newUiOptions or View.SYSTEM_UI_FLAG_VISIBLE
     //   newUiOptions = newUiOptions or View.IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS
        decorView.systemUiVisibility = newUiOptions
    }
}