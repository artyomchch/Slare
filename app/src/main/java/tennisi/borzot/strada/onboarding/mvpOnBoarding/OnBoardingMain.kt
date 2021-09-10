package tennisi.borzot.strada.onboarding.mvpOnBoarding

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import tennisi.borzot.strada.R
import tennisi.borzot.strada.databinding.ActivityOnBoardingMainBinding
import tennisi.borzot.strada.onboarding.OnBoardingViewPagerAdapter



class OnBoardingMain : AppCompatActivity(), OnBoardingInterface.View {

    private val binding: ActivityOnBoardingMainBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityOnBoardingMainBinding.inflate(layoutInflater)
    }


    private var presenter: OnBoardingPresenter? = null
    private var onBoardingViewPagerAdapter: OnBoardingViewPagerAdapter? = null
    var dataList: MutableList<OnBoardingData>? = null

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        presenter = OnBoardingPresenter(this)
        presenter?.createData(application)
        presenter?.savePrefData(application)
        dataList = presenter!!.getDataTitle()
        presenter!!.viewPager()
    }


    override fun initViewPager(mutableList: MutableList<OnBoardingData>) {
        onBoardingViewPagerAdapter = OnBoardingViewPagerAdapter(this, mutableList)
        binding.screenPager.adapter = onBoardingViewPagerAdapter
        binding.tabIndicator.setupWithViewPager(binding.screenPager)
    }


    override fun buttonNext() {
        binding.nextTv.setOnClickListener{
            presenter?.buttonNext(this, binding.screenPager)
        }
    }

    override fun tabNext() {
        binding.tabIndicator.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab!!.position == dataList?.size?.minus(1) ){
                    binding.nextTv.text = getString(R.string.get_started_text)
                }
                else{
                    binding.nextTv.text = getString(R.string.next_button)
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }
}