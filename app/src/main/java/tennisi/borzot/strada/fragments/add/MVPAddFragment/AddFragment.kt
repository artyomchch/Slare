package tennisi.borzot.strada.fragments.add.MVPAddFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.fragment_add.*
import tennisi.borzot.strada.R
import tennisi.borzot.strada.database.Cars
import tennisi.borzot.strada.fragments.add.floating_button.AddCarButtonFragment
import tennisi.borzot.strada.fragments.add.floating_button.FragmentButtonInterface
import tennisi.borzot.strada.fragments.add.floating_button.FragmentButtonPresenter
import tennisi.borzot.strada.fragments.add.listviewcars.AdapterCar


class AddFragment : Fragment(),  AddFragmentInterface.View  {
    private var presenter: AddFragmentPresenter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        presenter = AddFragmentPresenter(this)

        val view = inflater.inflate(R.layout.fragment_add, container, false)
        val fab : FloatingActionButton = view.findViewById(R.id.FAB_car)

        val realm: Realm = Realm.getDefaultInstance()
        val carListWarning: TextView = view.findViewById(R.id.about_list_of_car)
        val listview = view.findViewById<ListView>(R.id.list_view_cars)

        if (presenter!!.checkAuto()){
            carListWarning.visibility = View.VISIBLE
        }else
            carListWarning.visibility = View.INVISIBLE


        listview.adapter = context?.let { AdapterCar(it, R.layout.cars_list, presenter!!.getListCar()) }

        //listener on  new car
        realm.addChangeListener {
            presenter!!.notifyChanges()
            if (presenter!!.checkAuto()){
                carListWarning.visibility = View.VISIBLE
            }else
                carListWarning.visibility = View.INVISIBLE


            (listview.adapter as AdapterCar?)?.notifyDataSetChanged()

        }


        //add car fragment
        fab.setOnClickListener {
            if (!presenter!!.getFragment().isAdded){
               presenter!!.clearFragment()

            }
        }


        //delete or remake choose car
        listview.onItemClickListener = AdapterView.OnItemClickListener{ _, _, _, id ->
            presenter!!.saveData(id)
        }




        return view
    }

    override fun showFragment(carButtonFragment: AddCarButtonFragment) {
        carButtonFragment.show(childFragmentManager, "addButtom")
    }


}