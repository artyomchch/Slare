package tennisi.borzot.strada.onBoarding.mvpOnBoarding

import android.Manifest
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import tennisi.borzot.strada.R
import tennisi.borzot.strada.databinding.ActivityOnBoardingMainBinding
import tennisi.borzot.strada.onBoarding.OnBoardingViewPagerAdapter
import tennisi.borzot.strada.services.firebase.SignInActivity


class OnBoardingMain : AppCompatActivity(), OnBoardingInterface.View {

    private val binding: ActivityOnBoardingMainBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityOnBoardingMainBinding.inflate(layoutInflater)
    }

    private var presenter: OnBoardingPresenter? = null
    private var onBoardingViewPagerAdapter: OnBoardingViewPagerAdapter? = null
    var dataList: MutableList<OnBoardingData>? = null

    private val singlePermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
        when {
            granted -> {
                startActivityWithTransition()
            }
            !shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) -> {
                startActivityWithTransition()
            }
            else -> {
                startActivityWithTransition()
            }
        }
    }


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
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageSelected(position: Int) {
                if (position <= 1) {
                    binding.nextButton.text = getString(R.string.next_button)
                } else
                    binding.nextButton.text = getString(R.string.get_started_text)
            }
        })
    }

    override fun requestPermission() {
        if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
            // доступ к камере запрещен, нужно объяснить зачем нам требуется разрешение
        } else {
            singlePermission.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }

    }

    private fun startActivityWithTransition(){
        startActivity(Intent(this, SignInActivity::class.java))
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }
}