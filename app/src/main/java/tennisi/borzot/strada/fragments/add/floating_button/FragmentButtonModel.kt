package tennisi.borzot.strada.fragments.add.floating_button

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import io.realm.Realm
import io.realm.exceptions.RealmException
import tennisi.borzot.strada.R
import tennisi.borzot.strada.database.Cars
import java.io.ByteArrayOutputStream
import java.util.*
import kotlin.collections.ArrayList

class FragmentButtonModel: FragmentButtonInterface.Model {

    private var carId: String = ""
    private var carBrand: String = ""
    private var carModel: String = ""
    private var carDescription: String = ""
    private var remake: Boolean = false

    private var carUpdateBrand: String = ""
    private var carUpdateModel: String = ""
    private var carUpdateDescription: String = ""

    private lateinit var carPicture: ByteArray




    private val autoPicture: Map<String, Int> = mapOf("Acura" to R.drawable.acura, "Audi" to R.drawable.audi,
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

    private val carsTag: ArrayList<String> = arrayListOf("Acura", "Audi", "Bimota", "BMW", "Buick", "Cadillac", "Chevrolet",
        "Citroen", "Daelim", "Derbi", "Ducati", "Ferrari", "Fiat", "Ford", "Harley-Davidson",
        "Holden", "Honda", "Husqvarna", "Hyosung", "Hyundai", "Infiniti", "Jeep", "Kawasaki",
        "KIA", "KTM", "Kymco", "Lexus", "Mazda", "Mercedes-Benz", "Mercury", "Mini", "Mitsubishi",
        "Nissan", "Opel", "Peugeot", "Piaggio", "Pontiac", "Porsche", "Renault", "Smart", "Subaru",
        "Suzuki", "SYM Motors", "Tesla", "Toyota", "Triumph", "TVS", "Underground", "Vespa",
        "Victory", "Volvo", "Yamaha")




    val realm: Realm = Realm.getDefaultInstance()

    override fun addDataToDB(brand: String, model: String, description: String, imageCar: Drawable): Boolean {
        realm.beginTransaction()
        return try {
            val car = realm.createObject(Cars::class.java, UUID.randomUUID().toString())
            car.carBrand = brand
            car.carModel = model
            car.carDescription = description
            car.image = pictureToDB(imageCar)
            realm.commitTransaction()
            true
        }catch (e: RealmException){
            false
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

    override fun dataBaseUpdate(imageCar: Drawable) {
        val updateSelectCar  = realm.where(Cars::class.java).equalTo(
            "id", carId
        ).findAll()
        for (cars in updateSelectCar){
            realm.beginTransaction()
            if (carUpdateBrand!= ""){
                cars.carBrand = carUpdateBrand
            }
            if (carUpdateModel != ""){
                cars.carModel = carUpdateModel
            }
            if (carUpdateDescription != ""){
                cars.carDescription = carUpdateDescription
            }
            cars.image = pictureToDB(imageCar)
            realm.commitTransaction()
        }

    }

    override fun restoreData(argument: Bundle) {
        carId = argument.getString("cId")!!
        carBrand = argument.getString("cBrand")!!
        carModel = argument.getString("cModel")!!
        carDescription = argument.getString("cDescription")!!
        //  val carMode = arguments.getString("cMode")
        carPicture = argument.getByteArray("cImage")!!
        remake = argument.getBoolean("cMake")

    }

    override fun setCarId(): String = carId
    override fun setCarBrand(): String = carBrand
    override fun setCarModel(): String = carModel
    override fun setCarDescription(): String = carDescription



    override fun getUpdateCarBrand(updateBrand: String) {
        carUpdateBrand = updateBrand
    }

    override fun getUpdateCarModel(updateModel: String) {
        carUpdateModel = updateModel
    }

    override fun getUpdateCarDescription(updateDescription: String) {
        carUpdateDescription = updateDescription
    }


    override fun setCarPicture(): Bitmap = carPicture.size.let {
        BitmapFactory.decodeByteArray(carPicture, 0 ,
            it
        )
    }

    override fun getCarChoose(): Boolean = remake
    override fun getCarTag(): ArrayList<String> = carsTag

    override fun searchInputTags(inputCar: String): ArrayList<String> {
        val newArrayListCar: java.util.ArrayList<String> = arrayListOf()
        val newArrayListCarRubbish: java.util.ArrayList<String> = arrayListOf()
        for (i in carsTag){
            if (i.toLowerCase().indexOf(inputCar.toLowerCase())!= -1 && i.toLowerCase().indexOf(
                    inputCar.toLowerCase()
                ) == 0){
                newArrayListCar.add(i)
            }
            else if (i.toLowerCase().indexOf(inputCar.toLowerCase())!= -1){
                newArrayListCarRubbish.add(i)
            }
        }
        for (i in newArrayListCarRubbish){
            newArrayListCar.add(i)
        }


        return newArrayListCar
    }

    override fun searchInputPicture(inputCar: String): Int {
        for (i in autoPicture){
            if (i.key == inputCar){
                return i.value
            }
        }
        return autoPicture.getValue("NoAuto")

    }


    // перевод найденных картинок в байткод
    private fun pictureToDB(image: Drawable): ByteArray{
        val bitmap = (image as BitmapDrawable).bitmap
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }



}