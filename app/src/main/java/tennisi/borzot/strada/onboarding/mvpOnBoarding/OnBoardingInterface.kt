package tennisi.borzot.strada.onboarding.mvpOnBoarding

import android.app.Application
import android.content.Context
import androidx.viewpager.widget.ViewPager

interface OnBoardingInterface {

    interface View{
        fun buttonNext()
        fun tabNext()
        fun initViewPager(mutableList: MutableList<OnBoardingData>)
    }

    interface Presenter{
        fun savePrefData(application: Application)
        fun createData(application: Application)
        fun getDataTitle(): MutableList<OnBoardingData>
        fun buttonNext(context: Context, viewPager: ViewPager)
        fun viewPager()
    }

    interface Model{
        fun createData(application: Application)
        fun savePrefData(application: Application)
        fun getDataTitle(): MutableList<OnBoardingData>
    }
}