package tennisi.borzot.strada.fragments.add.floating_button

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import io.realm.Realm
import io.realm.exceptions.RealmException
import kotlinx.android.synthetic.main.fragment_add_car.*
import tennisi.borzot.strada.R
import tennisi.borzot.strada.database.Cars
import java.io.ByteArrayOutputStream
import java.util.*

class FragmentButtonModel: FragmentButtonInterface.Model {

    var carId: String = ""
    var carBrand: String = ""
    var carModel: String = ""
    var carDescription: String = ""
    var remake: Boolean = false
    lateinit var carPicture: ByteArray



    val autoPicture: Map<String, Int> = mapOf("Acura" to R.drawable.acura, "Audi" to R.drawable.audi,
    "Bimota" to R.drawable.bimota, "BMW" to R.drawable.bmw, "Buick" to R.drawable.buick,
    "Cadillac" to R.drawable.cadillac, "Chevrolet" to R.drawable.chevrolet, "Citroen" to R.drawable.citroen,
    "Daelim" to R.drawable.daelim, "Derbi" to R.drawable.derbi, "Ducati" to R.drawable.ducati,
    "Ferrari" to R.drawable.ferrari, "Fiat" to R.drawable.fiat, "Ford" to R.drawable.ford,
    "Harley-Davidson" to R.drawable.harleydavidson, "Holden" to R.drawable.holden, "Honda" to  R.drawable.hondaaa,
    "Husqvarna" to R.drawable.husqvarna, "Hyosung" to R.drawable.hyosung, "Hyundai" to  R.drawable.hyundai,
    "Infiniti" to R.drawable.infiniti, "Jeep" to R.drawable.jeep, "Kawasaki" to R.drawable.kawasaki,
    "KIA" to  R.drawable.kia, "KTM" to R.drawable.ktm, "Kymco" to R.drawable.kymco,
    "Lexus" to R.drawable.lexus, "Mazda" to R.drawable.mazda, "Mercedes-Benz" to  R.drawable.mercedesbenz,
    "Mercury" to R.drawable.mercury, "Mini" to R.drawable.mini, "Mitsubishi" to R.drawable.mitsubishi,
    "Nissan" to R.drawable.nissan, "Opel" to R.drawable.opel, "Peugeot" to R.drawable.peugeot,
    "Piaggio" to R.drawable.piaggio, "Pontiac" to R.drawable.pontiac, "Porsche" to R.drawable.porsche,
    "Renault" to R.drawable.renault, "Smart" to R.drawable.smart, "Subaru" to R.drawable.subaru,
    "Suzuki" to R.drawable.suzuki, "SYM Motors" to R.drawable.symmotor, "Tesla" to R.drawable.tesla,
    "Toyota" to R.drawable.toyota, "Triumph" to R.drawable.triumph, "TVS" to R.drawable.tvs,
    "Underground" to R.drawable.underground, "Vespa" to R.drawable.vespa, "Victory" to R.drawable.victory,
    "Volvo" to R.drawable.volvo, "Yamaha" to R.drawable.yamaha, "NoAuto" to R.drawable.noauto)

    val realm: Realm = Realm.getDefaultInstance()

    override fun addDataToDB(brand: String, model: String, description: String, imageCar: Drawable): Boolean {
        realm.beginTransaction()
        try {
            val car = realm.createObject(Cars::class.java, UUID.randomUUID().toString())
            car.name = brand
            car.description = model
            car.mode = description
            car.image = pictureToDB(imageCar)
            realm.commitTransaction()
            return true
        }catch (e: RealmException){
            return false
        }


    }

    override fun deleteDataToDB() {
        val deleteSelectCar  = realm.where(Cars::class.java).equalTo(
            "id", carId
        ).findAll()
        for (cars in deleteSelectCar) {
            realm.beginTransaction()
            cars.deleteFromRealm()
            realm.commitTransaction()
        }

    }

    override fun restoreData(argument: Bundle) {
        carId = argument.getString("cId")!!
        carBrand = argument.getString("cName")!!
        carModel = argument.getString("cDescription")!!
        //  val carMode = arguments.getString("cMode")
        val carPicture = argument.getByteArray("cImage")
        remake = argument.getBoolean("cMake")
        //  Log.d("War", "$carId      $carName     $carDescription     $carMode")
    }

    // перевод найденных картинок в байткод
    private fun pictureToDB(image: Drawable): ByteArray{
        val bitmap = (image as BitmapDrawable).bitmap
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        val bitmapdata: ByteArray = stream.toByteArray()
        return bitmapdata
    }


}