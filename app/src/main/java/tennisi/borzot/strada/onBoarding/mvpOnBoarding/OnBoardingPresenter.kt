package tennisi.borzot.strada.onBoarding.mvpOnBoarding

import android.app.Application
import android.content.Context
import androidx.viewpager.widget.ViewPager

class OnBoardingPresenter(_view: OnBoardingInterface.View) : OnBoardingInterface.Presenter {

    private var view: OnBoardingInterface.View = _view
    private var model: OnBoardingInterface.Model = OnBoardingModel()

    init {
        view.buttonNext()
        view.tabNext()
    }

    override fun savePrefData(application: Application) {
        model.savePrefData(application)
    }

    override fun createData(application: Application) {
        model.createData(application)
    }

    override fun getDataTitle(): MutableList<OnBoardingData> {
        return model.getDataTitle()
    }

    override fun buttonNext(context: Context, viewPager: ViewPager) {
        var position = viewPager.currentItem
        if (position < model.getDataTitle().size) {
            position++
            viewPager.currentItem = position
        }
        if (position == model.getDataTitle().size) {
            view.requestPermission()
        }
    }

    override fun viewPager() {
        view.initViewPager(model.getDataTitle())
    }




}