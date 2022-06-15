package tennisi.borzot.strada.services.speedService

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.os.Looper
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.android.gms.location.*
import kotlinx.coroutines.*
import tennisi.borzot.strada.R

class SpeedForegroundService : Service() {


    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    private lateinit var locationRequest: LocationRequest

    private lateinit var locationCallback: LocationCallback

    var onSpeedListenerChange: ((Float) -> Unit)? = null


    override fun onCreate() {
        super.onCreate()
        log("OnCreate")
        createNotificationChannel()
        startForeground(NOTIFICATION_ID, createNotification())
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        log("OnStartCommand")
        coroutineScope.launch {
            getLocationUpdates(true, this@SpeedForegroundService)
            for (i in 0 until 100) {
                delay(1000)
                log("Timer: $i")
            }
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }

    override fun onBind(intent: Intent?): IBinder {
       return LocalBinder()
    }


    private fun createNotificationChannel() {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    private fun createNotification() = NotificationCompat.Builder(this, CHANNEL_ID)
        .setContentTitle("Title")
        .setContentText("Text")
        .setSmallIcon(R.drawable.ic_app_icon)
        .build()

    fun log(text: String) {
        Log.d("Service", "ForegorundService: $text")
    }


    @SuppressLint("MissingPermission")
    private fun getLocationUpdates(state: Boolean, context: Context) {

        val fusedLocationProviderClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        locationRequest = LocationRequest.create().apply {
            interval = 100
            fastestInterval = 50
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            maxWaitTime = 100
        }
        if (state) {
            locationCallback = object : LocationCallback() {
                override fun onLocationResult(p0: LocationResult) {
                    if (p0.locations.isNotEmpty()) {
                        coroutineScope.launch {
                            onSpeedListenerChange?.invoke((p0.lastLocation.speed * 3.6).format(1))
                        }
                    }
                    super.onLocationResult(p0)
                }
            }
            LOCATION_CALL = locationCallback
            fusedLocationProviderClient.requestLocationUpdates(locationRequest, LOCATION_CALL, Looper.getMainLooper())
        }
    }

    fun Double.format(digits: Int) = ("%.${digits}f".format(this).toFloat())

    inner class LocalBinder: Binder(){

        fun getService() = this@SpeedForegroundService
    }


    companion object {

        private lateinit var LOCATION_CALL: LocationCallback

        private const val CHANNEL_ID = "channel_id"
        private const val CHANNEL_NAME = "channel_name"
        private const val NOTIFICATION_ID = 1

        fun newIntent(context: Context): Intent {
            return Intent(context, SpeedForegroundService::class.java)
        }

    }


}