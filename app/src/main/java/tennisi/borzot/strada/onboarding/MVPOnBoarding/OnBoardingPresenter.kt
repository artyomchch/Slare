package tennisi.borzot.strada.onboarding.MVPOnBoarding

import android.view.Window

class OnBoardingPresenter(_view: OnBoardingInterface.View): OnBoardingInterface.Presenter {

    private var view: OnBoardingInterface.View = _view
    private var model: OnBoardingInterface.Model = OnBoardingModel()

    override fun hideUI(window: Window) {
        model.hideUI(window)
    }





}