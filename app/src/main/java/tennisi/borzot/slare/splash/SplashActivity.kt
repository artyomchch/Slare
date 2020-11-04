package tennisi.borzot.slare.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import tennisi.borzot.slare.R
import tennisi.borzot.slare.onboarding.OnBoardingMain

@Suppress("DEPRECATION")
class SplashActivity : AppCompatActivity() {

    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideSystemUI()
        setContentView(R.layout.activity_splash)


        handler = Handler()
        handler.postDelayed({

            val intent = Intent(this, OnBoardingMain::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            finish()
        }, 1500)
    }





    private fun hideSystemUI() {
        val decorView: View = window.decorView
        val uiOptions = decorView.systemUiVisibility
        var newUiOptions = uiOptions
        newUiOptions = newUiOptions or View.SYSTEM_UI_FLAG_LOW_PROFILE
        //newUiOptions = newUiOptions or View.SYSTEM_UI_FLAG_FULLSCREEN
        newUiOptions = newUiOptions or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        //newUiOptions = newUiOptions or View.SYSTEM_UI_FLAG_IMMERSIVE
        newUiOptions = newUiOptions or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
     //   newUiOptions = newUiOptions or View.SYSTEM_UI_FLAG_VISIBLE
      //  newUiOptions = newUiOptions or View.IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS
        decorView.systemUiVisibility = newUiOptions
    }


}