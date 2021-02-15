package tennisi.borzot.strada.splash

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import tennisi.borzot.strada.R
import tennisi.borzot.strada.onboarding.MVPOnBoarding.OnBoardingMain

@Suppress("DEPRECATION")
class SplashActivity : AppCompatActivity(), SplashInterface.View {

    private var presenter: SplashPresenter? = null

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        presenter = SplashPresenter(this)
        presenter!!.hideUI(window)
        presenter!!.restorePrefData(this, intent)

    }


    override fun initView() {

    }

    override fun animation() {
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        finish()
    }



}