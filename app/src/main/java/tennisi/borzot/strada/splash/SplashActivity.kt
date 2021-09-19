package tennisi.borzot.strada.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tennisi.borzot.strada.onBoarding.mvpOnBoarding.OnBoardingMain

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
       // setTheme(R.style.SplashTheme)
        super.onCreate(savedInstanceState)
        val intent = Intent(this, OnBoardingMain::class.java)
        this.startActivity(intent)
    }
}