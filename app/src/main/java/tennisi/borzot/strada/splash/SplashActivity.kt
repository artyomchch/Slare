package tennisi.borzot.strada.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import tennisi.borzot.strada.homeScreen.presentation.MainActivity
import tennisi.borzot.strada.onBoarding.mvpOnBoarding.OnBoardingMain

class SplashActivity : AppCompatActivity() {

    private lateinit var splashViewModel: SplashViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        splashViewModel = ViewModelProvider(this)[SplashViewModel::class.java]
        splashViewModel.readFromDataStore.observe(this) {
            startSelectActivity(it)

        }
    }

    private fun startSelectActivity(valueActivity: String) {
        if (valueActivity == DataStoreRepository.FIRST)
            startActivity(Intent(this, MainActivity::class.java))
        else startActivity(Intent(this, OnBoardingMain::class.java))
        finish()
    }

    override fun onDestroy() {
        Log.d("TAG", "onDestroy: ")
        super.onDestroy()
    }
}