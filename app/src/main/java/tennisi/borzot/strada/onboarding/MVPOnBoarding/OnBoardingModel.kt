package tennisi.borzot.strada.onboarding.MVPOnBoarding

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.annotation.RequiresApi
import tennisi.borzot.strada.R

@Suppress("DEPRECATION")
class OnBoardingModel:  OnBoardingInterface.Model {

    var sharedPreference: SharedPreferences? = null
    var dataList: MutableList<OnBoardingData> = arrayListOf()

    override fun createData() {
        dataList.add(OnBoardingData("Check List",  "Possession her thoroughly remarkably terminated man continuing. Removed greater to do ability. You shy shall while but wrote marry.", R.drawable.checklist))
        dataList.add(OnBoardingData("Speedometer", "Was drawing natural fat respect husband. An as noisy an offer drawn blush place. These tried for way joy wrote witty.", R.drawable.speedometer))
        dataList.add(OnBoardingData("Speed limit", "Fulfilled direction use continual set him propriety continued. Saw met applauded favourite deficient engrossed concealed and her.", R.drawable.speedlimit))
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun hideUI(window: Window) {
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

        val decorView: View = window.decorView
        val uiOptions = decorView.systemUiVisibility
        var newUiOptions = uiOptions
        window.statusBarColor = Color.parseColor("#EFEFEF")
        newUiOptions = newUiOptions or View.SYSTEM_UI_FLAG_LOW_PROFILE
        newUiOptions = newUiOptions or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        newUiOptions = newUiOptions or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        decorView.systemUiVisibility = newUiOptions

    }



    override fun savePrefData(application: Application) {
        sharedPreference =  application.applicationContext.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val editor = sharedPreference!!.edit()
        editor.putBoolean("isFirstTimeRun", true)
        editor.apply()
    }

    override fun getDataTitle(): MutableList<OnBoardingData> = dataList




}