package tennisi.borzot.strada.fragments.speed

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.github.anastr.speedviewlib.Gauge
import com.github.anastr.speedviewlib.SpeedView
import com.github.anastr.speedviewlib.components.Style
import kotlinx.android.synthetic.main.fragment_speed.*
import tennisi.borzot.strada.R
import tennisi.borzot.strada.services.speedService.SpeedService
import java.util.jar.Manifest


class SpeedFragment : Fragment() {


    private val locationPermissionCode = 2


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_speed, container, false)
        val spedd: Button = view.findViewById(R.id.speed_button)
        val start: Button = view.findViewById(R.id.start_button)
        val speedService: SpeedService? = null
        val speedometer: SpeedView
        speedometer =  view.findViewById(R.id.speedView)
        var active = true









        speedometer.makeSections(15, Color.CYAN, Style.BUTT)
        speedometer.sections[0].color = Color.BLUE
        speedometer.sections[1].color = Color.BLUE
        speedometer.sections[2].color = Color.BLUE
        speedometer.sections[3].color = Color.BLUE
        speedometer.sections[4].color = Color.BLUE
        speedometer.sections[5].color = Color.BLUE
        speedometer.sections[6].color = Color.BLUE
        speedometer.sections[7].color = Color.BLUE
        speedometer.sections[8].color = Color.BLUE
        speedometer.sections[9].color = Color.BLUE
        speedometer.sections[10].color = Color.BLUE
        speedometer.sections[11].color = Color.RED
        speedometer.sections[12].color = Color.RED
        speedometer.sections[13].color = Color.RED
        speedometer.sections[14].color = Color.RED



        speedometer.ticks = arrayListOf(
            .0f, .0f, .0f, .0f, .0f, .0f, .0f, .0f, .0f,
            .0f, .0f, .0f, .0f, .0f, .0f
        )

        speedometer.onSpeedChangeListener = { gauge: Gauge, isSpeedUp: Boolean, isByTremble: Boolean ->



            if (active == true){
                when (speedometer.currentIntSpeed) {
                    0 -> {
                        speedometer.ticks = arrayListOf(
                            .0f, .0f, .0f, .0f, .0f, .0f, .0f, .0f, .0f,
                            .0f, .0f, .0f, .0f, .0f, .0f, .0f, .0f
                        )
                    }
                    10 -> {
                        speedometer.ticks = arrayListOf(
                            .0f, .066f, .0f, .0f, .0f, .0f, .0f, .0f, .0f,
                            .0f, .0f, .0f, .0f, .0f, .0f, .0f, .0f
                        )

                    }
                    20 -> {
                        speedometer.ticks = arrayListOf(
                            .0f, .066f, .133f, .0f, .0f, .0f, .0f, .0f, .0f,
                            .0f, .0f, .0f, .0f, .0f, .0f, .0f
                        )

                    }
                    30 -> {
                        speedometer.ticks = arrayListOf(
                            .0f, .066f, .133f, .2f, 0f, .0f, .0f, .0f, .0f,
                            .0f, .0f, .0f, .0f, .0f, .0f, .0f
                        )

                    }
                    40 -> {
                        speedometer.ticks = arrayListOf(
                            .0f, .066f, .133f, .2f, .266f, .0f, .0f, .0f, .0f,
                            .0f, .0f, .0f, .0f, .0f, .0f, .0f
                        )
                    }
                    50 -> {
                        speedometer.ticks = arrayListOf(
                            .0f, .066f, .133f, .2f, .266f, .333f, .0f, .0f, .0f,
                            .0f, .0f, .0f, .0f, .0f, .0f, .0f
                        )
                    }
                    60 -> {
                        speedometer.ticks = arrayListOf(
                            .0f, .066f, .133f, .2f, .266f, .333f, .4f, .0f, .0f,
                            .0f, .0f, .0f, .0f, .0f, .0f, .0f
                        )
                    }
                    70 -> {
                        speedometer.ticks = arrayListOf(
                            .0f, .066f, .133f, .2f, .266f, .333f, .4f, .466f, .0f,
                            .0f, .0f, .0f, .0f, .0f, .0f, .0f
                        )
                    }
                    80 -> {
                        speedometer.ticks = arrayListOf(
                            .0f, .066f, .133f, .2f, .266f, .333f, .4f, .466f, .53f,
                            .0f, .0f, .0f, .0f, .0f, .0f, .0f
                        )
                    }
                    90 -> {
                        speedometer.ticks = arrayListOf(
                            .0f, .066f, .133f, .2f, .266f, .333f, .4f, .466f, .53f,
                            .6f, .0f, .0f, .0f, .0f, .0f, .0f
                        )
                    }
                    100 -> {
                        speedometer.ticks = arrayListOf(
                            .0f, .066f, .133f, .2f, .266f, .333f, .4f, .466f, .53f,
                            .6f, .666f, .0f, .0f, .0f, .0f, .0f
                        )
                    }
                    110 -> {
                        speedometer.ticks = arrayListOf(
                            .0f, .066f, .133f, .2f, .266f, .333f, .4f, .466f, .53f,
                            .6f, .666f, .733f, .0f, .0f, .0f, .0f
                        )
                    }
                    120 -> {
                        speedometer.ticks = arrayListOf(
                            .0f, .066f, .133f, .2f, .266f, .333f, .4f, .466f, .53f,
                            .6f, .666f, .733f, .8f, .0f, .0f, .0f
                        )
                    }
                    130 -> {
                        speedometer.ticks = arrayListOf(
                            .0f, .066f, .133f, .2f, .266f, .333f, .4f, .466f, .53f,
                            .6f, .666f, .733f, .8f, .866f, .0f, .0f
                        )
                    }
                    140 -> {
                        speedometer.ticks = arrayListOf(
                            .0f, .066f, .133f, .2f, .266f, .333f, .4f, .466f, .53f,
                            .6f, .666f, .733f, .8f, .866f, .933f, .0f
                        )
                    }
                    150 -> {
                        speedometer.ticks = arrayListOf(
                            .0f, .066f, .133f, .2f, .266f, .333f, .4f, .466f, .53f,
                            .6f, .666f, .733f, .8f, .866f, .933f, .9999f
                        )
                        active = false
                        speedometer.speedPercentTo(0, 1200)

                    }
                }
            }
            else{
                if (speedometer.currentIntSpeed == 150){
                    speedometer.speedPercentTo(0, 1200)
                }

            }





        }
        spedd.setOnClickListener {
            speedometer.speedPercentTo(100, 1200)
            val intent = Intent(context, SpeedService::class.java)
            if (context != null) {
                context!!.stopService(intent)

            }
        }
        start.setOnClickListener {
            speedometer.speedPercentTo(100, 1200)
            val intent = Intent(context, SpeedService::class.java)
            if (context != null) {
                context!!.startService(intent)
            }
        }








        return view
    }



    fun dd(){
      //  speedService?.speedD?.toFloat()?.let { speedometer.speedTo(it) }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == locationPermissionCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }




}