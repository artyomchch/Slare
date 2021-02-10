package tennisi.borzot.strada.onboarding.MVPOnBoarding

import android.view.Window

interface OnBoardingInterface {

    interface View{
        fun initView()
    }

    interface Presenter{
        fun hideUI(window: Window)
    }

    interface Model{
        fun hideUI(window: Window)
    }
}