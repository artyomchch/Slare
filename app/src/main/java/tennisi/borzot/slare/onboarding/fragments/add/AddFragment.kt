package tennisi.borzot.slare.onboarding.fragments.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import tennisi.borzot.slare.R


class AddFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        val addCarButtonFragment = AddCarButtonFragment()
        val fab : FloatingActionButton


        fab = view.findViewById(R.id.FAB_car)



        fab.setOnClickListener {
            addCarButtonFragment.show(childFragmentManager, "addButtom")

        }



        return view


    }






}