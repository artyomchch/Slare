package tennisi.borzot.strada.services.speedService

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

class SpeedService : Service() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate() {
        super.onCreate()
        log("OnCreate")
        createNotificationChannel()
        startForeground(NOTIFICATION_ID, createNotification())

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        log("OnStartCommand")

        coroutineScope.launch {
            for (i in 0 until 100) {
                delay(1000)
                log("timer $i")
            }
            stopSelf()
        }

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
        log("OnDestroy")
    }


    override fun onBind(p0: Intent?): IBinder? {
        TODO()
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

        fun newIntent(context: Context): Intent {
            return Intent(context, SpeedService::class.java)
        }
    }

}