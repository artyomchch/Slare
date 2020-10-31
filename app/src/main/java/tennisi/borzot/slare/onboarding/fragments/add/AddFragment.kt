package tennisi.borzot.slare.onboarding.fragments.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_add.*
import tennisi.borzot.slare.R


class AddFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_add, container, false)
        var fab : FloatingActionButton
        fab = view.findViewById(R.id.FAB_car)


        fab.setOnClickListener {
            val addCarButtonFragment = AddCarButtonFragment()
            addCarButtonFragment.show(childFragmentManager, "addButtom")
        }


        return view


    }





}