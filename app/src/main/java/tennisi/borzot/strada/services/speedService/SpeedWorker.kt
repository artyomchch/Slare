package tennisi.borzot.strada.services.speedService

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Looper
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.*
import com.google.android.gms.location.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import tennisi.borzot.strada.R
import tennisi.borzot.strada.fragments.speed.LocationDTO

class SpeedWorker(
    context: Context,
    private val workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {


    private var fusedLocationProviderClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

    // globally declare LocationRequest
    private lateinit var locationRequest: LocationRequest

    // globally declare LocationCallback
    private lateinit var locationCallback: LocationCallback

    private val notificationManager = context.getSystemService(NotificationManager::class.java)

    private val notificationBuilder = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
        .setSmallIcon(R.drawable.audi)
        .setContentText(SPEED.toString())
        .setContentTitle("Important background job")

    val coroutineScope = CoroutineScope(Dispatchers.IO)

    override suspend fun doWork(): Result {
        log("doWork")

        createNotificationChannel()


        val state = workerParameters.inputData.getBoolean(STATE, false)

        getLocationUpdates(state)
        while (state) {
           // log(foregroundInfoAsync.toString())
        }

        return Result.success()
    }


    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = notificationManager?.getNotificationChannel(CHANNEL_ID)
            if (notificationChannel == null) {
                notificationManager?.createNotificationChannel(
                    NotificationChannel(
                        CHANNEL_ID, TAG, NotificationManager.IMPORTANCE_LOW
                    )
                )
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLocationUpdates(state: Boolean) {
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
                        log("${p0.lastLocation.speed.format(2)} ${Thread.currentThread()}")
                        coroutineScope.launch {
                            SPEED = p0.lastLocation.speed.format(2)
                            val notification = notificationBuilder.setContentText(SPEED.toString()).build()
                            val foregroundInfo = ForegroundInfo(NOTIFICATION_ID, notification)
                            setForeground(foregroundInfo)
                            EventBus.getDefault().post(LocationDTO(SPEED.toString()))
                        }

                    }
                    super.onLocationResult(p0)
                }
            }
            LOCATION_CALL = locationCallback

            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
        } else stopLocation()
    }

    fun Float.format(digits: Int) = "%.${digits}f".format(this).toFloat()

    private fun stopLocation() {
        fusedLocationProviderClient.removeLocationUpdates(LOCATION_CALL)
    }


    private fun log(mes: String) {
        Log.d("service tag", "SpeedWorker $mes")
    }

    companion object {

        private lateinit var LOCATION_CALL: LocationCallback
        private const val STATE = "state"
        const val WORK_NAME = "work name"
        private const val CHANNEL_ID = "channel_id"
        const val TAG = "ForegroundWorker"
        private const val NOTIFICATION_ID = 1

        var SPEED = 0f

        fun makeRequest(state: Boolean): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<SpeedWorker>()
                .setInputData(workDataOf(STATE to state))
                .setConstraints(makeConstraints())
                .build()

        }


        private fun makeConstraints() = Constraints.Builder()
            .build()


    }


}

