package tennisi.borzot.strada.services.speedService

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.IBinder
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import tennisi.borzot.strada.fragments.speed.SpeedFragment
import java.util.jar.Manifest

class SpeedService(): Service(), LocationListener {
    private lateinit var locationManager: LocationManager
    private var speedFragment: SpeedFragment? = null
    private val locationPermissionCode = 2
    var speedD: Int = 0



    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        Log.d("serviceSpeed", "служба создается ${Thread.currentThread()} ")
        getLocation()
        super.onCreate()
    }

    override fun onStart(intent: Intent?, startId: Int) {
        Log.d("serviceSpeed", "Видимо тоже что-то начинает")

        super.onStart(intent, startId)

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("serviceSpeed", "Служба начинает свой запуск")

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.d("serviceSpeed", "Служба, пока-пока ")
        super.onDestroy()
    }

    override fun onLocationChanged(location: Location) {
        location.latitude
        location.longitude
         speedD =  (location.speed * 3600 / 1000).toInt()
        speedFragment?.dd()
        Log.d("serviceSpeed", "onLocationChanged: ${location.latitude} , ${location.longitude}, $speedD ")
    }

    private fun getLocation() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if ((ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
           // ActivityCompat.requestPermissions(this, arrayOf(ACCESS_FINE_LOCATION), locationPermissionCode)
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1f, this)
    }
}