package tennisi.borzot.strada.fragments.speed

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.github.anastr.speedviewlib.Gauge
import com.github.anastr.speedviewlib.SpeedView
import com.github.anastr.speedviewlib.components.Style
import kotlinx.android.synthetic.main.fragment_speed.*
import tennisi.borzot.strada.R
import tennisi.borzot.strada.fragments.add.MVPAddFragment.AddFragmentPresenter
import tennisi.borzot.strada.services.speedService.SpeedReceiver
import tennisi.borzot.strada.services.speedService.SpeedService


class SpeedFragment : Fragment(), SpeedInterface.View {
    val speedReceiver = SpeedReceiver()
    private val locationPermissionCode = 2
    private var presenter: SpeedPresenter? = null
    private var active = true
    private var stopService = false


    override fun onResume() {
        super.onResume()
        activity?.registerReceiver(speedReceiver, IntentFilter("speed.broadcast.receiver"))

    }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        presenter = SpeedPresenter(this)
        val view = inflater.inflate(R.layout.fragment_speed, container, false)
        val stop: Button = view.findViewById(R.id.speed_button)
        val start: Button = view.findViewById(R.id.start_button)
        val speedometer: SpeedView = view.findViewById(R.id.speedView)


        setSectionTicks(speedometer)




        val receiver: BroadcastReceiver = object : BroadcastReceiver()
        {
            override fun onReceive(context: Context?, intent: Intent) {
                if(!active){
                    intent.getStringExtra("speed.broadcast.Message")?.let { speedometer.speedTo(it.toFloat()) }
                    speedometer.speedTo(speedReceiver.speedCalc(), 600)
                }

            }

        }
        activity?.registerReceiver(receiver, IntentFilter("speed.broadcast.receiver"))




        speedometer.onSpeedChangeListener = { gauge: Gauge, isSpeedUp: Boolean, isByTremble: Boolean ->
            startAnimationTicks(speedometer)
        }



        stop.setOnClickListener {
            val intent = Intent(context, SpeedService::class.java)
            if (context != null) {
                context!!.stopService(intent)
                //stopService = true
                speedometer.speedPercentTo(100, 1200)

                speedometer.onSpeedChangeListener = { gauge: Gauge, isSpeedUp: Boolean, isByTremble: Boolean ->

                    if (speedometer.currentIntSpeed ==150){
                        stopAnimationTicks(speedometer)
                    }

                }
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






    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == locationPermissionCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }




    private fun setSectionTicks(speedView: SpeedView) {
        speedView.makeSections(15, Color.CYAN, Style.BUTT)
        speedView.sections[0].color = Color.BLUE
        speedView.sections[1].color = Color.BLUE
        speedView.sections[2].color = Color.BLUE
        speedView.sections[3].color = Color.BLUE
        speedView.sections[4].color = Color.BLUE
        speedView.sections[5].color = Color.BLUE
        speedView.sections[6].color = Color.BLUE
        speedView.sections[7].color = Color.BLUE
        speedView.sections[8].color = Color.BLUE
        speedView.sections[9].color = Color.BLUE
        speedView.sections[10].color = Color.BLUE
        speedView.sections[11].color = Color.RED
        speedView.sections[12].color = Color.RED
        speedView.sections[13].color = Color.RED
        speedView.sections[14].color = Color.RED


        speedView.ticks = arrayListOf(
            .0f, .0f, .0f, .0f, .0f, .0f, .0f, .0f, .0f,
            .0f, .0f, .0f, .0f, .0f, .0f
        )

    }

    private fun startAnimationTicks(speedView: SpeedView){
        if (active && !stopService){
            when (speedView.currentIntSpeed) {
                0 -> {
                    speedView.ticks = arrayListOf(
                        .0f, .0f, .0f, .0f, .0f, .0f, .0f, .0f, .0f,
                        .0f, .0f, .0f, .0f, .0f, .0f, .0f, .0f
                    )
                }
                10 -> {
                    speedView.ticks = arrayListOf(
                        .0f, .066f, .0f, .0f, .0f, .0f, .0f, .0f, .0f,
                        .0f, .0f, .0f, .0f, .0f, .0f, .0f, .0f
                    )

                }
                20 -> {
                    speedView.ticks = arrayListOf(
                        .0f, .066f, .133f, .0f, .0f, .0f, .0f, .0f, .0f,
                        .0f, .0f, .0f, .0f, .0f, .0f, .0f
                    )

                }
                30 -> {
                    speedView.ticks = arrayListOf(
                        .0f, .066f, .133f, .2f, 0f, .0f, .0f, .0f, .0f,
                        .0f, .0f, .0f, .0f, .0f, .0f, .0f
                    )

                }
                40 -> {
                    speedView.ticks = arrayListOf(
                        .0f, .066f, .133f, .2f, .266f, .0f, .0f, .0f, .0f,
                        .0f, .0f, .0f, .0f, .0f, .0f, .0f
                    )
                }
                50 -> {
                    speedView.ticks = arrayListOf(
                        .0f, .066f, .133f, .2f, .266f, .333f, .0f, .0f, .0f,
                        .0f, .0f, .0f, .0f, .0f, .0f, .0f
                    )
                }
                60 -> {
                    speedView.ticks = arrayListOf(
                        .0f, .066f, .133f, .2f, .266f, .333f, .4f, .0f, .0f,
                        .0f, .0f, .0f, .0f, .0f, .0f, .0f
                    )
                }
                70 -> {
                    speedView.ticks = arrayListOf(
                        .0f, .066f, .133f, .2f, .266f, .333f, .4f, .466f, .0f,
                        .0f, .0f, .0f, .0f, .0f, .0f, .0f
                    )
                }
                80 -> {
                    speedView.ticks = arrayListOf(
                        .0f, .066f, .133f, .2f, .266f, .333f, .4f, .466f, .53f,
                        .0f, .0f, .0f, .0f, .0f, .0f, .0f
                    )
                }
                90 -> {
                    speedView.ticks = arrayListOf(
                        .0f, .066f, .133f, .2f, .266f, .333f, .4f, .466f, .53f,
                        .6f, .0f, .0f, .0f, .0f, .0f, .0f
                    )
                }
                100 -> {
                    speedView.ticks = arrayListOf(
                        .0f, .066f, .133f, .2f, .266f, .333f, .4f, .466f, .53f,
                        .6f, .666f, .0f, .0f, .0f, .0f, .0f
                    )
                }
                110 -> {
                    speedView.ticks = arrayListOf(
                        .0f, .066f, .133f, .2f, .266f, .333f, .4f, .466f, .53f,
                        .6f, .666f, .733f, .0f, .0f, .0f, .0f
                    )
                }
                120 -> {
                    speedView.ticks = arrayListOf(
                        .0f, .066f, .133f, .2f, .266f, .333f, .4f, .466f, .53f,
                        .6f, .666f, .733f, .8f, .0f, .0f, .0f
                    )
                }
                130 -> {
                    speedView.ticks = arrayListOf(
                        .0f, .066f, .133f, .2f, .266f, .333f, .4f, .466f, .53f,
                        .6f, .666f, .733f, .8f, .866f, .0f, .0f
                    )
                }
                140 -> {
                    speedView.ticks = arrayListOf(
                        .0f, .066f, .133f, .2f, .266f, .333f, .4f, .466f, .53f,
                        .6f, .666f, .733f, .8f, .866f, .933f, .0f
                    )
                }
                150 -> {
                    speedView.ticks = arrayListOf(
                        .0f, .066f, .133f, .2f, .266f, .333f, .4f, .466f, .53f,
                        .6f, .666f, .733f, .8f, .866f, .933f, .9999f
                    )
                    active = false
                    speedView.speedPercentTo(0, 1200)

                }
            }
        }
        else if (!active && !stopService){
            if (speedView.currentIntSpeed == 150){
                speedView.speedPercentTo(0, 1200)
            }
            else if (speedView.currentIntSpeed == 0){
                speedView.speedTextColor = Color.parseColor("#77B2D8")
                speedView.unitTextColor = Color.parseColor("#000000")
            }

        }


    }


    private fun stopAnimationTicks(speedView: SpeedView){

            when (speedView.currentIntSpeed) {
                0 -> {
                    speedView.ticks = arrayListOf(
                        .0f, .0f, .0f, .0f, .0f, .0f, .0f, .0f, .0f,
                        .0f, .0f, .0f, .0f, .0f, .0f, .0f, .0f
                    )
                }
                10 -> {
                    speedView.ticks = arrayListOf(
                        .0f, .066f, .0f, .0f, .0f, .0f, .0f, .0f, .0f,
                        .0f, .0f, .0f, .0f, .0f, .0f, .0f, .0f
                    )

                }
                20 -> {
                    speedView.ticks = arrayListOf(
                        .0f, .066f, .133f, .0f, .0f, .0f, .0f, .0f, .0f,
                        .0f, .0f, .0f, .0f, .0f, .0f, .0f
                    )

                }
                30 -> {
                    speedView.ticks = arrayListOf(
                        .0f, .066f, .133f, .2f, 0f, .0f, .0f, .0f, .0f,
                        .0f, .0f, .0f, .0f, .0f, .0f, .0f
                    )

                }
                40 -> {
                    speedView.ticks = arrayListOf(
                        .0f, .066f, .133f, .2f, .266f, .0f, .0f, .0f, .0f,
                        .0f, .0f, .0f, .0f, .0f, .0f, .0f
                    )
                }
                50 -> {
                    speedView.ticks = arrayListOf(
                        .0f, .066f, .133f, .2f, .266f, .333f, .0f, .0f, .0f,
                        .0f, .0f, .0f, .0f, .0f, .0f, .0f
                    )
                }
                60 -> {
                    speedView.ticks = arrayListOf(
                        .0f, .066f, .133f, .2f, .266f, .333f, .4f, .0f, .0f,
                        .0f, .0f, .0f, .0f, .0f, .0f, .0f
                    )
                }
                70 -> {
                    speedView.ticks = arrayListOf(
                        .0f, .066f, .133f, .2f, .266f, .333f, .4f, .466f, .0f,
                        .0f, .0f, .0f, .0f, .0f, .0f, .0f
                    )
                }
                80 -> {
                    speedView.ticks = arrayListOf(
                        .0f, .066f, .133f, .2f, .266f, .333f, .4f, .466f, .53f,
                        .0f, .0f, .0f, .0f, .0f, .0f, .0f
                    )
                }
                90 -> {
                    speedView.ticks = arrayListOf(
                        .0f, .066f, .133f, .2f, .266f, .333f, .4f, .466f, .53f,
                        .6f, .0f, .0f, .0f, .0f, .0f, .0f
                    )
                }
                100 -> {
                    speedView.ticks = arrayListOf(
                        .0f, .066f, .133f, .2f, .266f, .333f, .4f, .466f, .53f,
                        .6f, .666f, .0f, .0f, .0f, .0f, .0f
                    )
                }
                110 -> {
                    speedView.ticks = arrayListOf(
                        .0f, .066f, .133f, .2f, .266f, .333f, .4f, .466f, .53f,
                        .6f, .666f, .733f, .0f, .0f, .0f, .0f
                    )
                }
                120 -> {
                    speedView.ticks = arrayListOf(
                        .0f, .066f, .133f, .2f, .266f, .333f, .4f, .466f, .53f,
                        .6f, .666f, .733f, .8f, .0f, .0f, .0f
                    )
                }
                130 -> {
                    speedView.ticks = arrayListOf(
                        .0f, .066f, .133f, .2f, .266f, .333f, .4f, .466f, .53f,
                        .6f, .666f, .733f, .8f, .866f, .0f, .0f
                    )
                }
                140 -> {
                    speedView.ticks = arrayListOf(
                        .0f, .066f, .133f, .2f, .266f, .333f, .4f, .466f, .53f,
                        .6f, .666f, .733f, .8f, .866f, .933f, .0f
                    )
                }
                150 -> {
                    speedView.ticks = arrayListOf(
                        .0f, .066f, .133f, .2f, .266f, .333f, .4f, .466f, .53f,
                        .6f, .666f, .733f, .8f, .866f, .933f, .9999f
                    )
//                    active = true
//                    stopService = true
                    speedView.speedPercentTo(0, 1200)

                }
            }






    }

}