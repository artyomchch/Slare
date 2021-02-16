package tennisi.borzot.strada.splash

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.Window
import androidx.core.content.ContextCompat.startActivity
import tennisi.borzot.strada.MainActivity
import tennisi.borzot.strada.R
import tennisi.borzot.strada.onboarding.MVPOnBoarding.OnBoardingMain
import java.util.logging.Handler

@Suppress("DEPRECATION")
class SplashPresenter(_view: SplashInterface.View): SplashInterface.Presenter {
    private var view: SplashInterface.View = _view
    private var model: SplashInterface.Model = SplashModel()
    private val handler = android.os.Handler()


    init {
        view.initView()
    }

    override fun hideUI(window: Window) {
        model.hideUI(window)
    }

    override fun restorePrefData(context: Context, intent: Intent) {

        handler.postDelayed({
            if (model.prefData(context)){
                context.startActivity(Intent(context.applicationContext, MainActivity::class.java))
                view.animation()
            }
            else {
                context.startActivity(Intent(context.applicationContext, OnBoardingMain::class.java))
                view.animation()
            }
        }, 1500)



    }




}