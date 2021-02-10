package tennisi.borzot.strada.splash

import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.annotation.RequiresApi

@Suppress("DEPRECATION")
class SplashModel: SplashInterface.Model {



    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun hideUI(window: Window) {
        hideSystemUI(window)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun hideSystemUI(window: Window) {
        // for status bar
       // val window_: Window = window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)


        val decorView: View = window.decorView
        val uiOptions = decorView.systemUiVisibility
        var newUiOptions = uiOptions
        window.statusBarColor = Color.parseColor("#111111")
        newUiOptions = newUiOptions or View.SYSTEM_UI_FLAG_LOW_PROFILE
        //newUiOptions = newUiOptions or View.SYSTEM_UI_FLAG_FULLSCREEN
        newUiOptions = newUiOptions or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        //newUiOptions = newUiOptions or View.SYSTEM_UI_FLAG_IMMERSIVE
        newUiOptions = newUiOptions or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        //   newUiOptions = newUiOptions or View.SYSTEM_UI_FLAG_VISIBLE
        //  newUiOptions = newUiOptions or View.IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS
        decorView.systemUiVisibility = newUiOptions
        Log.d("aaa", "hideSystemUI: ")
    }






}