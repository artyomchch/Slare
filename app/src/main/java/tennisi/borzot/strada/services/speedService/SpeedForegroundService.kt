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


    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private lateinit var locationRequest: LocationRequest

    private lateinit var locationCallback: LocationCallback

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    var onSpeedListenerChange: ((Float) -> Unit)? = null


    override fun onCreate() {
        super.onCreate()
        log("OnCreate")
        createNotificationChannel()
        startForeground(NOTIFICATION_ID, createNotification())
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val state = intent?.getBooleanExtra(START_STOP_FLAG, false) ?: false
        log("OnStartCommand")
        if (state) {
            coroutineScope.launch {
                getLocationUpdates()
            }
        }
        else stopLocation()

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
        .setContentTitle("Speed service activate")
        .setContentText("Now you can check your speed: ")
        .setSmallIcon(R.drawable.ic_app_icon)
        .build()

    fun log(text: String) {
        Log.d("Service", "ForegorundService: $text")
    }


    @SuppressLint("MissingPermission")
    private fun getLocationUpdates() {

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        locationRequest = LocationRequest.create().apply {
            interval = 100
            fastestInterval = 50
            priority = Priority.PRIORITY_HIGH_ACCURACY
            maxWaitTime = 100
        }
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult) {
                if (p0.locations.isNotEmpty()) {
                    coroutineScope.launch {
                        p0.lastLocation?.speed?.times(3.6)?.let { onSpeedListenerChange?.invoke(it.format(1)) }
                        Log.d("location", "onLocationResult: ${p0.lastLocation?.speed?.times(3.6)}")
                    }
                }
                super.onLocationResult(p0)
            }
        }
        LOCATION_CALL = locationCallback
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, LOCATION_CALL, Looper.getMainLooper())

    }

    fun Double.format(digits: Int) = ("%.${digits}f".format(this).toFloat())

    inner class LocalBinder : Binder() {
        fun getService() = this@SpeedForegroundService
    }

    private fun stopLocation() {
        fusedLocationProviderClient.removeLocationUpdates(LOCATION_CALL)
        coroutineScope.cancel()
    }


    companion object {

        private lateinit var LOCATION_CALL: LocationCallback

        private const val CHANNEL_ID = "channel_id"
        private const val CHANNEL_NAME = "channel_name"
        private const val NOTIFICATION_ID = 1

        private const val START_STOP_FLAG = "start_stop"

        fun newIntent(context: Context, state: Boolean): Intent {
            return Intent(context, SpeedForegroundService::class.java).apply {
                putExtra(START_STOP_FLAG, state)
            }
        }

    }


}