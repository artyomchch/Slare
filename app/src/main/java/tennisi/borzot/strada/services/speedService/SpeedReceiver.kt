package tennisi.borzot.strada.services.speedService

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class SpeedReceiver : BroadcastReceiver() {

    private var speed: Float = 0.0F

    override fun onReceive(context: Context, intent: Intent) {
//        Toast.makeText(context, "Текущая скорость: " +
//                intent.getStringExtra("speed.broadcast.Message"),
//            Toast.LENGTH_SHORT).show()
        speed = intent.getStringExtra("speed.broadcast.Message")!!.toFloat()
        Log.d("serviceSpeed", intent.getStringExtra("speed.broadcast.Message").toString())
    }

    fun speedCalc():Float{
        return speed
    }
}