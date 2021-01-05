package tennisi.borzot.strada.fragments.speed

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.anastr.speedviewlib.SpeedView
import com.github.anastr.speedviewlib.components.Section
import com.github.anastr.speedviewlib.components.Style
import tennisi.borzot.strada.R

class SpeedFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_speed, container, false)


        val speedometer: SpeedView
        speedometer =  view.findViewById(R.id.speedView)
        speedometer.speedTo(250.0F, 8000)

        speedometer.makeSections(5, Color.CYAN, Style.BUTT)
        speedometer.sections[0].color = Color.GREEN
        speedometer.sections[1].color = Color.BLUE
        speedometer.sections[2].color = Color.RED

//        speedometer.clearSections()
//        speedometer.addSections(Section(0f, .1f, Color.LTGRAY)
//            , Section(.1f, .4f, Color.YELLOW)
//            , Section(.4f, .75f, Color.BLUE)
//            , Section(.75f, .9f, Color.RED))

        return view
    }

}