package tennisi.borzot.strada.splash

import android.util.Log
import android.view.Window

class SplashPresenter(_view: SplashInterface.View): SplashInterface.Presenter {
    private var view: SplashInterface.View = _view
    private var model: SplashInterface.Model = SplashModel()


    init {
        //view.initView()
    }

    override fun hideUI(window: Window) {
        model.hideUI(window)
        Log.d("ddd", "hideUITrue: ")
    }


}