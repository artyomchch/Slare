package tennisi.borzot.strada.fragments.add.MVPAddFragment

import android.os.Bundle
import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.where
import tennisi.borzot.strada.database.Cars
import tennisi.borzot.strada.fragments.add.floating_button.AddCarButtonFragment

class AddFragmentModel: AddFragmentInterface.Model {
    val realm: Realm = Realm.getDefaultInstance()
    private val listCar = mutableListOf<Cars>()
    private var carlist: RealmResults<Cars> = realm.where<Cars>().findAll()
    private var carButtonFragment = AddCarButtonFragment()

    override fun getListCar(): MutableList<Cars> {
        return listCar
    }

    override fun checkAuto(): Boolean {
        return realm.isEmpty
    }

    override fun getAutoFromDb() {
        listCar.addAll(realm.copyFromRealm(carlist))
    }

    override fun notifyChanges() {
        listCar.clear()
        carlist = realm.where<Cars>().findAll()
        listCar.addAll(realm.copyFromRealm(carlist))
    }

    override fun setEmptyData() {
        sendDataToCarButtonFragment("", "", "", "", "",
            byteArrayOf(), false)
    }

    override fun setFullData(id: Long) {
        sendDataToCarButtonFragment(
            listCar.elementAt(id.toInt()).id.toString(),
            listCar[id.toInt()].carBrand.toString(),
            listCar[id.toInt()].carModel.toString(),
            listCar[id.toInt()].carDescription.toString(),
            listCar[id.toInt()].mode.toString(),
            listCar[id.toInt()].image,
            true
        )
    }


    private fun sendDataToCarButtonFragment(
        id: String?,
        brand: String,
        model: String,
        description: String,
        mode: String,
        img: ByteArray?,
        remake: Boolean
    ) {
        val newFragment = AddCarButtonFragment()
        val bundle = Bundle()
        bundle.putString("cId", id)
        bundle.putString("cBrand", brand)
        bundle.putString("cModel", model)
        bundle.putString("cDescription", description)
        bundle.putString("cMode", mode)
        bundle.putByteArray("cImage", img )
        bundle.putBoolean("cMake", remake)
        newFragment.arguments = bundle
        carButtonFragment = newFragment

    }

    override fun getFragment(): AddCarButtonFragment = carButtonFragment




}