package tennisi.borzot.strada.services.speedService

import android.app.IntentService
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.*
import tennisi.borzot.strada.R

class SpeedIntentService2 : IntentService(NAME) {



    override fun onCreate() {
        super.onCreate()
        log("OnCreate")
        setIntentRedelivery(true)


    }


    override fun onHandleIntent(p0: Intent?) {
        log("onHandleIntent")
        val page = p0?.getIntExtra(PAGE, 0) ?: 0
        for (i in 0 until 5) {
            Thread.sleep(1000)
            log("timer $i  $page")
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        log("OnDestroy")
    }



    private fun log(mes: String) {
        Log.d("service tag", mes)
    }


    companion object {


        private const val NAME = "SpeedIntentService"
        private const val PAGE = "page"


        fun newIntent(context: Context, page: Int): Intent {
            return Intent(context, SpeedIntentService2::class.java).apply {
                putExtra(PAGE, page)
            }
        }
    }

}