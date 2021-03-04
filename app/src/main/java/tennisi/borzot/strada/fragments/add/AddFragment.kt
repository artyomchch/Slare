package tennisi.borzot.strada.fragments.add

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
import tennisi.borzot.strada.R
import tennisi.borzot.strada.database.Cars
import tennisi.borzot.strada.fragments.add.floating_button.AddCarButtonFragment
import tennisi.borzot.strada.fragments.add.listviewcars.AdapterCar


class AddFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        val addCarButtonFragment = AddCarButtonFragment()
        val fab : FloatingActionButton
        val realm: Realm = Realm.getDefaultInstance()
        fab = view.findViewById(R.id.FAB_car)

        val carListWarning: TextView = view.findViewById(R.id.about_list_of_car)

        val listview = view.findViewById<ListView>(R.id.list_view_cars)
        val listCar = mutableListOf<Cars>()
        listCar.clear()


        var carlist: RealmResults<Cars> = realm.where<Cars>().findAll()
        checkAuto(carlist, carListWarning)
        listCar.addAll(realm.copyFromRealm(carlist))
        listview.adapter = context?.let { AdapterCar(it, R.layout.cars_list, listCar) }








        //listener on  new car
        realm.addChangeListener {
            listCar.clear()
            carlist = it.where<Cars>().findAll()
            listCar.addAll(it.copyFromRealm(carlist))
            Log.d("vac", carlist.isEmpty().toString())
            checkAuto(carlist, carListWarning)

            (listview.adapter as AdapterCar?)?.notifyDataSetChanged()

        }


        //add car fragment
        fab.setOnClickListener {
            if (!addCarButtonFragment.isAdded){
              //  addCarButtonFragment.show(childFragmentManager, "addButtom")
                sendDataToCarButtonFragment("", "", "", "", "", byteArrayOf(), false)

            }
        }


        //delete or remake choose car
        listview.onItemClickListener = AdapterView.OnItemClickListener{ _, _, _, id ->

            sendDataToCarButtonFragment(
                listCar.elementAt(id.toInt()).id.toString(),
                listCar[id.toInt()].carBrand.toString(),
                listCar[id.toInt()].carModel.toString(),
                listCar[id.toInt()].carDescription.toString(),
                listCar[id.toInt()].mode.toString(),
                listCar[id.toInt()].image,
                true
            )
            //deleteOrRemakeCar(listCar, realm, id, addCarButtonFragment)
        }






        return view
    }


    fun deleteOrRemakeCar(
        listCar: MutableList<Cars>,
        realm: Realm,
        idCar: Long,
        carFragment: AddCarButtonFragment
    )
    {

        val deleteSelectCar  = realm.where(Cars::class.java).equalTo(
            "id",
            listCar.elementAt(idCar.toInt()).id.toString()
        ).findAll()
        for (cars in deleteSelectCar) {
            realm.beginTransaction()
            cars.deleteFromRealm()
            realm.commitTransaction()
        }
        carFragment.show(childFragmentManager, "addButtom")
    }


    fun sendDataToCarButtonFragment(id: String?, brand: String, model: String, description: String, mode: String, img: ByteArray?, remake: Boolean ){
        val carButtonFragment = AddCarButtonFragment()
        val bundle = Bundle()
        bundle.putString("cId", id)
        bundle.putString("cBrand", brand)
        bundle.putString("cModel", model)
        bundle.putString("cDescription", description)
        bundle.putString("cMode", mode)
        bundle.putByteArray("cImage", img )
        bundle.putBoolean("cMake", remake)
        carButtonFragment.arguments = bundle
        carButtonFragment.show(childFragmentManager, "addButtom")
    }



    fun checkAuto(realm: RealmResults<Cars>, carTextView: TextView){
        if (realm.isEmpty()){
            carTextView.visibility = View.VISIBLE
        }
        else
            carTextView.visibility = View.INVISIBLE
    }



}