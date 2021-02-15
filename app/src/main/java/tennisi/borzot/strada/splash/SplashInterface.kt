package tennisi.borzot.strada.splash

import android.content.Context
import android.content.Intent
import android.view.Window

interface SplashInterface {

    interface View {
        fun initView()
        fun animation()
    }

    interface Presenter {
        fun hideUI(window: Window)
        fun restorePrefData(context: Context, intent: Intent)
    }

    interface Model {
        fun hideUI(window: Window)
        fun prefData(context: Context): Boolean

    }


}