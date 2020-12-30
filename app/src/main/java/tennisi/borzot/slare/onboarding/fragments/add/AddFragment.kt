package tennisi.borzot.slare.onboarding.fragments.add

import android.os.Bundle
import android.os.Message
import android.util.Log
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
        checkAuto(carlist, carListWarning)
        list.addAll(realm.copyFromRealm(carlist))
        listview.adapter = context?.let { AdapterCar(it, R.layout.cars_list, list) }
        // проверка на существования машины для TextView

       // carListWarning.visibility = View.INVISIBLE



        //listener on  new car
        realm.addChangeListener {
            list.clear()
            carlist = it.where<Cars>().findAll()
            list.addAll(it.copyFromRealm(carlist))
            Log.d("vac", carlist.isEmpty().toString())
            checkAuto(carlist, carListWarning)

            (listview.adapter as AdapterCar?)?.notifyDataSetChanged()

        }


        //add car fragment
        fab.setOnClickListener {
            addCarButtonFragment.show(childFragmentManager, "addButtom")

        }

        //delete or remake choose car
        listview.onItemClickListener = AdapterView.OnItemClickListener{ parent, view, position, id ->
            deleteOrRemakeCar(list, realm, id, addCarButtonFragment)
        }






        return view
    }


    fun deleteOrRemakeCar(listCar: MutableList<Cars>, realm: Realm, idCar: Long, carFragment: AddCarButtonFragment ){

        val deleteSelectCar  = realm.where(Cars::class.java).equalTo("id", listCar.elementAt(idCar.toInt()).id.toString()).findAll()
        for (cars in deleteSelectCar) {
            realm.beginTransaction()
            cars.deleteFromRealm()
            realm.commitTransaction()
        }
        carFragment.show(childFragmentManager, "addButtom")
    }

    fun checkAuto(realm: RealmResults<Cars>, carTextView: TextView){
        if (realm.isEmpty()){
            carTextView.visibility = View.VISIBLE
        }
        else
            carTextView.visibility = View.INVISIBLE
    }



}