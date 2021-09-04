package tennisi.borzot.strada.onboarding.mvpOnBoarding

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import tennisi.borzot.strada.R

class OnBoardingModel:  OnBoardingInterface.Model {

    var sharedPreference: SharedPreferences? = null
    var dataList: MutableList<OnBoardingData> = arrayListOf()

    override fun createData() {
        dataList.add(OnBoardingData("Check List",  "Possession her thoroughly remarkably terminated man continuing. Removed greater to do ability. " +
                "You shy shall while but wrote marry.", R.drawable.checklist))
        dataList.add(OnBoardingData("Speedometer", "Was drawing natural fat respect husband. An as noisy an offer drawn blush place. These tried for " +
                "way joy wrote witty.", R.drawable.speedometer))
        dataList.add(OnBoardingData("Speed limit", "Fulfilled direction use continual set him propriety continued. Saw met applauded favourite " +
                "deficient engrossed concealed and her.", R.drawable.speedlimit))
    }

    override fun savePrefData(application: Application) {
        sharedPreference =  application.applicationContext.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val editor = sharedPreference!!.edit()
        editor.putBoolean("isFirstTimeRun", true)
        editor.apply()
    }

    override fun getDataTitle(): MutableList<OnBoardingData> = dataList




}