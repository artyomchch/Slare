package tennisi.borzot.strada.onboarding.mvpOnBoarding

import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
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

        binding.nextButton.typeface = Typeface.createFromAsset(assets, "montserrat.ttf")
    }


    override fun initViewPager(mutableList: MutableList<OnBoardingData>) {
        onBoardingViewPagerAdapter = OnBoardingViewPagerAdapter(this, mutableList)
        binding.screenPager.adapter = onBoardingViewPagerAdapter
        binding.wormDotsIndicator.setViewPager(binding.screenPager)
    }


    override fun buttonNext() {
        binding.nextButton.setOnClickListener {
            presenter?.buttonNext(this, binding.screenPager)
        }
    }

    override fun tabNext() {
        binding.screenPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                if (position <= 1) {
                    binding.nextButton.text = getString(R.string.next_button)
                } else
                    binding.nextButton.text = getString(R.string.get_started_text)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }
}