package tennisi.borzot.strada.onboarding.MVPOnBoarding

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import tennisi.borzot.strada.R
import tennisi.borzot.strada.onboarding.OnBoardingViewPagerAdapter

@Suppress("DEPRECATION")
class OnBoardingMain : AppCompatActivity(), OnBoardingInterface.View {

    private var presenter: OnBoardingPresenter? = null

    private var onBoardingViewPagerAdapter: OnBoardingViewPagerAdapter? = null
    private var tabLayout: TabLayout? = null
    private var onBoardingViewPager: ViewPager? = null
    var next: TextView? = null

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

        dataList = presenter!!.getDataTitle()
        presenter!!.viewPager()

    }


    override fun initViewPager(mutableList: MutableList<OnBoardingData>) {
        onBoardingViewPagerAdapter = OnBoardingViewPagerAdapter(this, mutableList)
        onBoardingViewPager?.adapter = onBoardingViewPagerAdapter
        tabLayout?.setupWithViewPager(onBoardingViewPager)

    }


    override fun buttonNext() {
        next!!.setOnClickListener(){
            presenter?.buttonNext(this, onBoardingViewPager!!)

        }
    }

    override fun tabNext() {
        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{

            @SuppressLint("SetTextI18n")
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab!!.position == dataList?.size?.minus(1) ){
                    next?.text = "Get Started"
                }
                else{
                    next?.text = "Next"
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })
    }


}