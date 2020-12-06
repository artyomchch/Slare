package tennisi.borzot.slare.onboarding.fragments.add

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.drawable.toDrawable
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import tennisi.borzot.slare.R
import tennisi.borzot.slare.database.Cars
import tennisi.borzot.slare.onboarding.fragments.add.listviewcars.AdapterCar
import tennisi.borzot.slare.onboarding.fragments.add.listviewcars.ModelCars


class AddFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        val addCarButtonFragment = AddCarButtonFragment()
        val fab : FloatingActionButton
        var realm: Realm = Realm.getDefaultInstance()
    //    val carListWarning: TextView
     //   carListWarning = view.findViewById(R.id.about_list_of_car)
        //realm

        fab = view.findViewById(R.id.FAB_car)

        var carListWarning: TextView = view.findViewById<TextView>(R.id.about_list_of_car)
        //database data



//        if (result1.toString()!= "[]"){
//            carListWarning.visibility = View.INVISIBLE
//        }


        var listview = view.findViewById<ListView>(R.id.list_view_cars)
        var list = mutableListOf<Cars>()

        list.clear()
        var carlist: RealmResults<Cars> = realm.where<Cars>().findAll()
        list.addAll(realm.copyFromRealm(carlist))

        listview.adapter = context?.let { AdapterCar(it, R.layout.cars_list, list) }
       // (listview.adapter as AdapterCar?)?.notifyDataSetChanged()




        realm.addChangeListener {
            list.clear()
            carlist = it.where<Cars>().findAll()
            list.addAll(it.copyFromRealm(carlist))
            (listview.adapter as AdapterCar?)?.notifyDataSetChanged()

        }


        fab.setOnClickListener {
            addCarButtonFragment.show(childFragmentManager, "addButtom")

        }

        addCar(view, realm)









        return view
    }


    fun addCar(view: View, realm: Realm){







//        listview.setOnItemClickListener{ parent: AdapterView<*>, view:View, position:Int, id->
//            if (position == 0){
//                Toast.makeText(context, "you click on idk what 1 ", Toast.LENGTH_LONG).show()
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