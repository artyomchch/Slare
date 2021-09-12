package tennisi.borzot.strada.onboarding.mvpOnBoarding

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import tennisi.borzot.strada.R

class OnBoardingModel : OnBoardingInterface.Model {

    var sharedPreference: SharedPreferences? = null
    var dataList: MutableList<OnBoardingData> = arrayListOf()

    override fun createData(application: Application) {
        dataList.add(
            OnBoardingData(
                application.getString(R.string.speed_limit),
                application.getString(R.string.desc_speed_limit),
                R.drawable.ic_speed_limit
            )
        )
        dataList.add(
            OnBoardingData(
                application.getString(R.string.destructed),
                application.getString(R.string.desc_destructed),
                R.drawable.ic_destructed
            )
        )
        dataList.add(
            OnBoardingData(
                application.getString(R.string.permissions),
                application.getString(R.string.desc_permissions),
                R.drawable.ic_permission
            )
        )
    }

    override fun savePrefData(application: Application) {
        sharedPreference = application.applicationContext.getSharedPreferences(application.getString(R.string.pref), Context.MODE_PRIVATE)
        val editor = sharedPreference!!.edit()
        editor.putBoolean("isFirstTimeRun", true)
        editor.apply()
    }

    override fun getDataTitle(): MutableList<OnBoardingData> = dataList

}