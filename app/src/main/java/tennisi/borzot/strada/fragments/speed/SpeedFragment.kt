package tennisi.borzot.strada.fragments.speed

import android.graphics.Color
import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.github.anastr.speedviewlib.Gauge
import com.github.anastr.speedviewlib.SpeedView
import com.github.anastr.speedviewlib.components.Section
import com.github.anastr.speedviewlib.components.Style
import kotlinx.android.synthetic.main.fragment_speed.*
import tennisi.borzot.strada.R

class SpeedFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_speed, container, false)
        val spedd: Button = view.findViewById(R.id.speed_button)

        val speedometer: SpeedView
        speedometer =  view.findViewById(R.id.speedView)


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

        speedometer.speedPercentTo(100, 1200)
        speedometer.tickNumber = 0

        speedometer.onSpeedChangeListener = { gauge: Gauge, isSpeedUp: Boolean, isByTremble: Boolean ->
           if (speedometer.currentIntSpeed == 150){
               speedometer.speedPercentTo(0, 1200)
               speedometer.tickNumber = 16
           }

        }
        spedd.setOnClickListener {
            speedometer.speedPercentTo(100, 1200)
        }



        return view
    }

}