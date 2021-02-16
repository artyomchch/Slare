package tennisi.borzot.strada.onboarding.MVPOnBoarding

import android.app.Application
import android.content.Context
import android.view.Window
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

interface OnBoardingInterface {

    interface View{
        fun buttonNext()
        fun tabNext()
        fun initViewPager(mutableList: MutableList<OnBoardingData>)
    }

    interface Presenter{
        fun hideUI(window: Window)
        fun savePrefData(application: Application)
        fun getDataTitle(): MutableList<OnBoardingData>
        fun buttonNext(context: Context, viewPager: ViewPager)
        fun viewPager()
    }

    interface Model{
        fun createData()
        fun hideUI(window: Window)
        fun savePrefData(application: Application)
        fun getDataTitle(): MutableList<OnBoardingData>
    }
}