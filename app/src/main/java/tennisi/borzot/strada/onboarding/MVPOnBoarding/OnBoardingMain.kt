package tennisi.borzot.strada.onboarding.MVPOnBoarding

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import tennisi.borzot.strada.MainActivity
import tennisi.borzot.strada.R
import tennisi.borzot.strada.onboarding.OnBoardingViewPagerAdapter

@Suppress("DEPRECATION")
class OnBoardingMain : AppCompatActivity(), OnBoardingInterface.View {

    private var presenter: OnBoardingPresenter? = null

    var onBoardingViewPagerAdapter: OnBoardingViewPagerAdapter? = null
    var tabLayout: TabLayout? = null
    var onBoardingViewPager: ViewPager? = null
    var next: TextView? = null
    var position: Int = 0
    var dataList: MutableList<OnBoardingData>? = null

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_main)
        tabLayout = findViewById(R.id.tab_indicator)
        next = findViewById(R.id.next_tv)
        onBoardingViewPager = findViewById(R.id.screenPager)


        presenter = OnBoardingPresenter(this)

        presenter?.savePrefData(application)
        presenter!!.hideUI(window)

        dataList = presenter!!.showDataSlide()
        setOnBoardingViewPagerAdapter(dataList!!)







        next!!.setOnClickListener(){
            if (position < dataList!!.size){
                position++
                onBoardingViewPager?.currentItem = position
            }
            if (position == dataList?.size){

                val i = Intent(applicationContext, MainActivity::class.java)
                startActivity(i)
            }
        }

        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            @SuppressLint("SetTextI18n")
            override fun onTabSelected(tab: TabLayout.Tab?) {
                position = tab!!.position
                if (tab.position == dataList?.size?.minus(1) ){
                    next?.text = "Get Started"
                }
                else{
                    next?.text = "Next"
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

    }








    private fun setOnBoardingViewPagerAdapter(onBoardingData: List<OnBoardingData>){
        onBoardingViewPagerAdapter = OnBoardingViewPagerAdapter(this, onBoardingData)
        onBoardingViewPager?.adapter = onBoardingViewPagerAdapter
        tabLayout?.setupWithViewPager(onBoardingViewPager)
        position = onBoardingViewPager!!.currentItem
    }






    override fun initView() {



    }
}