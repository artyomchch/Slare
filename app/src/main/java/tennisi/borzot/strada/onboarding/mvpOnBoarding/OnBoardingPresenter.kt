package tennisi.borzot.strada.onboarding.mvpOnBoarding

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.viewpager.widget.ViewPager
import tennisi.borzot.strada.MainActivity

class OnBoardingPresenter(_view: OnBoardingInterface.View) : OnBoardingInterface.Presenter {

    private var view: OnBoardingInterface.View = _view
    private var model: OnBoardingInterface.Model = OnBoardingModel()
    private var showPermission = false

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
            //context.startActivity(Intent(context.applicationContext, MainActivity::class.java))
        }
    }

    override fun viewPager() {
        view.initViewPager(model.getDataTitle())
    }




}