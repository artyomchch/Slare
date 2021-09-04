package tennisi.borzot.strada.onboarding.mvpOnBoarding

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import tennisi.borzot.strada.databinding.ActivityOnBoardingMainBinding
import tennisi.borzot.strada.onboarding.OnBoardingViewPagerAdapter
import tennisi.borzot.strada.onboarding.viewModel.OnBoardingViewModel


class OnBoardingMain : AppCompatActivity(), OnBoardingInterface.View {

    private val binding: ActivityOnBoardingMainBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityOnBoardingMainBinding.inflate(layoutInflater)
    }
    lateinit var  boardingMainModel: OnBoardingViewModel


    private var presenter: OnBoardingPresenter? = null
    private var onBoardingViewPagerAdapter: OnBoardingViewPagerAdapter? = null
    var dataList: MutableList<OnBoardingData>? = null

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        boardingMainModel = ViewModelProvider(this).get(OnBoardingViewModel::class.java)


        presenter = OnBoardingPresenter(this)
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
            @SuppressLint("SetTextI18n")
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab!!.position == dataList?.size?.minus(1) ){
                    binding.nextTv.text = "Get Started"
                }
                else{
                    binding.nextTv.text = "Next"
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }
}