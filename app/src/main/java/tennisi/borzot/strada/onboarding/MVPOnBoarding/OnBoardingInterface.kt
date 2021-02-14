package tennisi.borzot.strada.onboarding.MVPOnBoarding

import android.app.Application
import android.view.Window

interface OnBoardingInterface {

    interface View{
        fun initView()
    }

    interface Presenter{
        fun hideUI(window: Window)
        fun showDataSlide(): MutableList<OnBoardingData>
        fun savePrefData(application: Application)
    }

    interface Model{

        fun hideUI(window: Window)
        fun getDataSlide():MutableList<OnBoardingData>
        fun savePrefData(application: Application)
    }
}