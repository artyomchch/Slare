package tennisi.borzot.slare.onboarding.fragments.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.where
import tennisi.borzot.slare.R
import tennisi.borzot.slare.database.Cars
import tennisi.borzot.slare.onboarding.fragments.add.listviewcars.AdapterCar


class AddFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        val addCarButtonFragment = AddCarButtonFragment()
        val fab : FloatingActionButton
        var realm: Realm = Realm.getDefaultInstance()
        fab = view.findViewById(R.id.FAB_car)

        val carListWarning: TextView = view.findViewById(R.id.about_list_of_car)

        var listview = view.findViewById<ListView>(R.id.list_view_cars)
        val list = mutableListOf<Cars>()
        list.clear()


        var carlist: RealmResults<Cars> = realm.where<Cars>().findAll()
        list.addAll(realm.copyFromRealm(carlist))
        listview.adapter = context?.let { AdapterCar(it, R.layout.cars_list, list) }
        // проверка на существования машины для TextView
        carListWarning.visibility = View.INVISIBLE




        realm.addChangeListener {
            list.clear()
            carlist = it.where<Cars>().findAll()
            list.addAll(it.copyFromRealm(carlist))
            (listview.adapter as AdapterCar?)?.notifyDataSetChanged()

        }


        fab.setOnClickListener {
            addCarButtonFragment.show(childFragmentManager, "addButtom")

        }

//       listview.setOnClickListener() {
//           Toast.makeText(context, "you click on idk what 1 ", Toast.LENGTH_LONG).show()
//       }

        listview.onItemClickListener = AdapterView.OnItemClickListener{ parent, view, position, id ->
            val selectedItemText = parent.getItemAtPosition(position)
            Toast.makeText(context, "Selected : $selectedItemText     -> position: $position", Toast.LENGTH_SHORT).show()
            addCarButtonFragment.show(childFragmentManager, "addButtom")
            realm.executeTransaction { realm -> realm.deleteAll() }


        }






        return view
    }


    fun deleteOrRemakeCar(view: View, realm: Realm){


//       listview.setOnItemClickListener{ parent: AdapterView<*>, view:View, position:Int, id->
//            if (position == 0){
//
//            }
//            if (position == 1){
//                Toast.makeText(context, "you click on idk what 2 ", Toast.LENGTH_LONG).show()
//            }
//            if (position == 2){
//                Toast.makeText(context, "you click on idk what 3", Toast.LENGTH_LONG).show()
//            }
//            if (position == 3){
//                Toast.makeText(context, "you click on idk what 4", Toast.LENGTH_LONG).show()
//            }
//
//        }



    }



}