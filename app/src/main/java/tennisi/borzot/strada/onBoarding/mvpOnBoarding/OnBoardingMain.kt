package tennisi.borzot.strada.onBoarding.mvpOnBoarding

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import tennisi.borzot.strada.R
import tennisi.borzot.strada.databinding.ActivityOnBoardingMainBinding
import tennisi.borzot.strada.onBoarding.OnBoardingViewPagerAdapter
import tennisi.borzot.strada.services.firebase.authentication.SignInActivity


class OnBoardingMain : AppCompatActivity(), OnBoardingInterface.View {

    private val binding: ActivityOnBoardingMainBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityOnBoardingMainBinding.inflate(layoutInflater)
    }

    private val locationPermissionRequestLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions(),
        ::onGotPermissionsForLocation
    )

    private var presenter: OnBoardingPresenter? = null
    private var onBoardingViewPagerAdapter: OnBoardingViewPagerAdapter? = null
    private var dataList: MutableList<OnBoardingData>? = null


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
        locationPermissionRequestLauncher.launch(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION))
    }

    private fun onGotPermissionsForLocation(grantResults: Map<String, Boolean>) {
        if (grantResults.entries.all { it.value }) {
            startActivityWithTransition()
            Toast.makeText(this, getString(R.string.permission_granted), Toast.LENGTH_SHORT).show()
        } else {
            if (!shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION)) {
                askUserForOpeningAppSettings()
            } else {
                Toast.makeText(this, getString(R.string.permission_denied), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun askUserForOpeningAppSettings() {
        val appSettingsIntent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", packageName, null)
        )
        if (packageManager.resolveActivity(appSettingsIntent, PackageManager.MATCH_DEFAULT_ONLY) == null) {
            Toast.makeText(this, getString(R.string.forever_denied), Toast.LENGTH_SHORT).show()
        } else {
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.permission_denied))
                .setMessage(getString(R.string.warning_permission_denied_dialog))
                .setNegativeButton(getString(R.string.open_app_settings)) { _, _ ->
                    startActivity(appSettingsIntent)
                }
                .setPositiveButton(R.string.next_button) {_, _ ->
                    startActivityWithTransition()
                }
                .create()
                .show()
        }
    }

    private fun startActivityWithTransition(){
        startActivity(Intent(this, SignInActivity::class.java))
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }
}