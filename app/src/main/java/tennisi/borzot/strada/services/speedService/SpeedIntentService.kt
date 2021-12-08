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

class SpeedIntentService : IntentService(NAME) {



    override fun onCreate() {
        super.onCreate()
        log("OnCreate")
        setIntentRedelivery(true)
        createNotificationChannel()
        startForeground(NOTIFICATION_ID, createNotification())

    }


    override fun onHandleIntent(p0: Intent?) {
        log("onHandleIntent")
        for (i in 0 until 5) {
            Thread.sleep(1000)
            log("timer $i")
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        log("OnDestroy")
    }



    private fun log(mes: String) {
        Log.d("service tag", mes)
    }

    private fun createNotificationChannel() {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }

    }

    private fun createNotification() = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
        .setContentTitle("Title")
        .setContentText("text")
        .setSmallIcon(R.drawable.audi)
        .build()

    companion object {

        private const val NOTIFICATION_ID = 1
        private const val CHANNEL_ID = "channel_id"
        private const val CHANNEL_NAME = "channel_name"
        private const val NAME = "SpeedIntentService"

        fun newIntent(context: Context): Intent {
            return Intent(context, SpeedIntentService::class.java)
        }
    }

}