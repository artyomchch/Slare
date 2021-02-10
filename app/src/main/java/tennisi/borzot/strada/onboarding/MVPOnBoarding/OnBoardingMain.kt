package tennisi.borzot.strada.onboarding.MVPOnBoarding

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
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
    var position = 0
    var sharedPreference: SharedPreferences ? = null


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = OnBoardingPresenter(this)
        presenter!!.hideUI(window)

        if (restorePrefData()){
            val i = Intent(applicationContext, MainActivity::class.java)
            startActivity(i)
        }
        setContentView(R.layout.activity_on_boarding_main)
        tabLayout = findViewById(R.id.tab_indicator)
        next = findViewById(R.id.next_tv)



        val onBoardingData:MutableList<OnBoardingData> = ArrayList()
        onBoardingData.add(OnBoardingData("Check List",  "Possession her thoroughly remarkably terminated man continuing. Removed greater to do ability. You shy shall while but wrote marry.", R.drawable.checklist))
        onBoardingData.add(OnBoardingData("Speedometer", "Was drawing natural fat respect husband. An as noisy an offer drawn blush place. These tried for way joy wrote witty.", R.drawable.speedometer))
        onBoardingData.add(OnBoardingData("Speed limit", "Fulfilled direction use continual set him propriety continued. Saw met applauded favourite deficient engrossed concealed and her.", R.drawable.speedlimit))

        setOnBoardingViewPagerAdapter(onBoardingData)

        position = onBoardingViewPager!!.currentItem


        next?.setOnClickListener(){
            if (position < onBoardingData.size){
                position++
                onBoardingViewPager!!.currentItem = position
            }
            if (position == onBoardingData.size){
                savePrefData()
                val i = Intent(applicationContext, MainActivity::class.java)
                startActivity(i)
            }
        }

        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            @SuppressLint("SetTextI18n")
            override fun onTabSelected(tab: TabLayout.Tab?) {
                position = tab!!.position
                if (tab.position == onBoardingData.size -1 ){
                    next!!.text = "Get Started"
                }
                else{
                    next!!.text = "Next"
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

    }




    private fun setOnBoardingViewPagerAdapter(onBoardingData: List<OnBoardingData>){
        onBoardingViewPager = findViewById(R.id.screenPager)
        onBoardingViewPagerAdapter = OnBoardingViewPagerAdapter(this, onBoardingData)
        onBoardingViewPager!!.adapter = onBoardingViewPagerAdapter
        tabLayout?.setupWithViewPager(onBoardingViewPager)
    }

    @SuppressLint("CommitPrefEdits")
    private fun savePrefData(){
        sharedPreference = applicationContext.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val editor = sharedPreference!!.edit()
        editor.putBoolean("isFirstTimeRun", true)
        editor.apply()
    }

    private fun restorePrefData(): Boolean{
        sharedPreference = applicationContext.getSharedPreferences("pref", Context.MODE_PRIVATE)
        return sharedPreference!!.getBoolean("isFirstTimeRun", false)
    }



    override fun initView() {

    }
}