package tennisi.borzot.slare.onboarding.fragments.add

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.realm.Realm
import io.realm.kotlin.where
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
        //realm
        var realm: Realm = Realm.getDefaultInstance()
        fab = view.findViewById(R.id.FAB_car)

        fab.setOnClickListener {
            addCarButtonFragment.show(childFragmentManager, "addButtom")
        }

        addCar(view, realm)









        return view
    }


    fun addCar(view: View, realm: Realm){
        var listview = view.findViewById<ListView>(R.id.list_view_cars)
        var list = mutableListOf<ModelCars>()
        //database data
        val query = realm.where<Cars>()
        val result1 = query.findAll()

        for (i in result1){
            list.add(ModelCars(i.name, i.mode, i.description, i.image))
            println(i.image)
        }



        listview.adapter = context?.let { AdapterCar(it, R.layout.cars_list, list) }



        listview.setOnItemClickListener{ parent: AdapterView<*>, view:View, position:Int, id->
            if (position == 0){
                Toast.makeText(context, "you click on idk what 1 ", Toast.LENGTH_LONG).show()
            }
            if (position == 1){
                Toast.makeText(context, "you click on idk what 2 ", Toast.LENGTH_LONG).show()
            }
            if (position == 2){
                Toast.makeText(context, "you click on idk what 3", Toast.LENGTH_LONG).show()
            }
            if (position == 3){
                Toast.makeText(context, "you click on idk what 4", Toast.LENGTH_LONG).show()
            }

        }
    }



}