package tennisi.borzot.strada.onboarding.MVPOnBoarding

import android.app.Application
import android.content.Context
import android.content.Intent
import android.view.Window
import androidx.viewpager.widget.ViewPager
import tennisi.borzot.strada.MainActivity

class OnBoardingPresenter(_view: OnBoardingInterface.View): OnBoardingInterface.Presenter {

    private var view: OnBoardingInterface.View = _view
    private var model: OnBoardingInterface.Model = OnBoardingModel()

    init {
       model.createData()
        view.buttonNext()
        view.tabNext()
    }


    override fun hideUI(window: Window) {
        model.hideUI(window)
    }


    override fun savePrefData(application: Application) {
        model.savePrefData(application)
    }

    override fun getDataTitle(): MutableList<OnBoardingData> {
        return model.getDataTitle()
    }

    override fun buttonNext(context: Context, viewPager: ViewPager) {
        var  position = viewPager.currentItem
        if (position < model.getDataTitle().size){
            position++
            viewPager.currentItem = position
        }
        if (position == model.getDataTitle().size){
            context.startActivity(Intent(context.applicationContext, MainActivity::class.java))
        }
    }



    override fun viewPager() {
        view.initViewPager(model.getDataTitle())
    }


}