package tennisi.borzot.strada.splash

import android.view.Window

interface SplashInterface {

    interface View {
        fun initView()
    }

    interface Presenter {
        fun hideUI(window: Window)
    }

    interface Model {
        fun hideUI(window: Window)


    }


}