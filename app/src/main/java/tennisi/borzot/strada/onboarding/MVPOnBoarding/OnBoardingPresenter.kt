package tennisi.borzot.strada.onboarding.MVPOnBoarding

import android.app.Application
import android.view.Window

class OnBoardingPresenter(_view: OnBoardingInterface.View): OnBoardingInterface.Presenter {

    private var view: OnBoardingInterface.View = _view
    private var model: OnBoardingInterface.Model = OnBoardingModel()

    init {
        view.initView()
    }


    override fun hideUI(window: Window) {
        model.hideUI(window)
    }

    override fun showDataSlide(): MutableList<OnBoardingData> {
        return model.getDataSlide()
    }

    override fun savePrefData(application: Application) {
        model.savePrefData(application)
    }




}