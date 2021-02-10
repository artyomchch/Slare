package tennisi.borzot.strada.fragments.add.floating_button



import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.core.view.isVisible
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import io.realm.Realm
import io.realm.exceptions.RealmException
import kotlinx.android.synthetic.main.fragment_add_car.*
import kotlinx.android.synthetic.main.fragment_add_car.view.*
import me.gujun.android.taggroup.TagGroup
import tennisi.borzot.strada.R
import tennisi.borzot.strada.database.Cars
import tennisi.borzot.strada.strings.AppStrings
import java.io.ByteArrayOutputStream
import java.util.*


class AddCarButtonFragment(): BottomSheetDialogFragment() {

    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val appStrings = AppStrings()
        val rbLeftButton : AppCompatRadioButton
        val rbRightButton : AppCompatRadioButton
        val view = inflater.inflate(R.layout.fragment_add_car, container, false)
        var carId: String? = ""
        var carName: String? = ""
        var carDescription: String? = ""
        var remake: Boolean = true


        var updateCarBrand: String? = ""
        var updateCarDescription: String? = ""

        val mTagGroup = view.findViewById(R.id.tag_group) as TagGroup
        val carImage = view.findViewById(R.id.imageAuto) as ImageView
        val carEditBrand = view.findViewById(R.id.edit_car_brand) as EditText
        val carEditName = view.findViewById(R.id.edit_car_name) as EditText
        val carCancel = view.findViewById(R.id.cancel) as Button
        val carDelete = view.findViewById(R.id.delete) as Button
        val carReCancel = view.findViewById(R.id.car_cancel) as Button
        val carUpdate = view.findViewById(R.id.update) as Button
        val carSave = view.findViewById(R.id.save) as Button
        // RadioButton
        var radioMode: String? = "auto"

        //realm
        val realm: Realm = Realm.getDefaultInstance()

        rbLeftButton = view.findViewById(R.id.rbLeft)
        rbRightButton = view.findViewById(R.id.rbRight)



        // передача аргументов с другого фрагмента
        val arguments = arguments
        if (arguments!= null){
            carId = arguments.getString("cId")
            carName = arguments.getString("cName")
            carDescription = arguments.getString("cDescription")
            val carMode = arguments.getString("cMode")
            val carPicture = arguments.getByteArray("cImage")
            remake = arguments.getBoolean("cMake")
            Log.d("War", "$carId      $carName     $carDescription     $carMode")
                carEditBrand.setText(carName)
                carEditName.setText(carDescription)

            if (remake){
                carDelete.visibility = View.VISIBLE
                carCancel.visibility =  View.GONE
                carReCancel.visibility = View.VISIBLE
                carSave.visibility = View.GONE


                Log.d("War", "remake works!!,  $remake")
            }
            else {
                carDelete.visibility = View.GONE
                carCancel.visibility = View.VISIBLE
                Log.d("War", "unremake works!!,  $remake")
            }
                // перевод с байткода в битмэп
            carImage.setImageBitmap(carPicture?.size?.let {
                BitmapFactory.decodeByteArray(carPicture, 0 ,
                    it
                )
            })
        }






        //Слушатель на тэги
        mTagGroup.setTags(appStrings.cars)
        mTagGroup.setOnTagClickListener(object : TagGroup.OnTagClickListener {
            override fun onTagClick(tag: String?) {
                edit_car_brand.setText(tag)
                mTagGroup.setTags(arrayListOf())
                edit_car_brand.setSelection(tag.toString().length)

            }
        })


        //слушатель на EditText Brand Edit
        carEditBrand.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {

                //work with tags
                mTagGroup.setTags(searchCarTags(appStrings.cars, p0.toString()))
                imageAuto.setImageResource(
                    appStrings.pictureCars[searchCarPicture(
                        appStrings.cars,
                        p0.toString()
                    )]
                )
                if (mTagGroup.tags.isNotEmpty()) {
                    if (p0.toString().contains(mTagGroup.tags[0])) {
                        tag_group.visibility = View.GONE
                    } else tag_group.visibility = View.VISIBLE
                } else tag_group.visibility = View.GONE

                //works with button view
                if (remake){
                    if (p0.toString() != carName){
                        carReCancel.visibility = View.GONE
                        carUpdate.visibility = View.VISIBLE
                        updateCarBrand = p0.toString()
                    }
                    else {
                        carUpdate.visibility = View.GONE
                        carReCancel.visibility = View.VISIBLE
                        updateCarBrand = ""
                    }
                }


            }
        })


        carEditName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if (remake){
                    if (p0.toString() != carDescription){
                        carReCancel.visibility = View.GONE
                        carUpdate.visibility = View.VISIBLE
                        updateCarDescription = p0.toString()
                    }
                    else {
                        carUpdate.visibility = View.GONE
                        carReCancel.visibility = View.VISIBLE
                        updateCarDescription = ""
                    }
                }

            }

        })







        //Слушатель на RadioButton
        rbLeftButton.setOnClickListener {
                view.rbLeft.setTextColor(Color.WHITE)
                view.rbRight.setTextColor(Color.parseColor("#77B2D8"))
                radioMode = "auto"
        }

        rbRightButton.setOnClickListener{
                view.rbLeft.setTextColor(Color.parseColor("#77B2D8"))
                view.rbRight.setTextColor(Color.WHITE)
                radioMode = "manual"
        }



        //save button
        carSave.setOnClickListener {
            addDBCar(realm, radioMode)
        }


        //cancel button
        carCancel.setOnClickListener {
            dismiss()
        }

        //delete button
        carDelete.setOnClickListener {
            deleteCar(carId, realm)
        }

        //another cancel button
        carReCancel.setOnClickListener {
            dismiss()
        }

        //update button
        carUpdate.setOnClickListener {
            updateCar(carId, realm, updateCarBrand, updateCarDescription)
        }



        return view
    }





    //add database
    fun addDBCar(realm: Realm, mode: String?){
        realm.beginTransaction()
        try {
            val car = realm.createObject(Cars::class.java, UUID.randomUUID().toString())
            car.name = edit_car_brand.text.toString()
            car.description = edit_car_name.text.toString()
            car.mode = mode
            car.image = pictureToDB(imageAuto.drawable)

            Toast.makeText(
                context,
                "Realm works: ${car.name.toString()} \n ${car.description} \n $mode",
                Toast.LENGTH_LONG
            ).show()
            realm.commitTransaction()
        }catch (e: RealmException){
           Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
            Log.e("Errrorr", e.message.toString())
        }
        dismiss()
    }


    //удаление из базы данных
    fun deleteCar(car: String?, realm: Realm) {
        val deleteSelectCar  = realm.where(Cars::class.java).equalTo(
            "id", car
        ).findAll()
        for (cars in deleteSelectCar) {
            realm.beginTransaction()
            cars.deleteFromRealm()
            realm.commitTransaction()
        }
        dismiss()
        Toast.makeText(context, "Delete success ", Toast.LENGTH_LONG).show()
    }

    fun updateCar(car: String?, realm: Realm, updateCarBrand: String?, updateCarDescription: String?){
        val updateSelectCar  = realm.where(Cars::class.java).equalTo(
            "id", car
        ).findAll()
        for (cars in updateSelectCar){
            realm.beginTransaction()
            if (updateCarBrand!= ""){
                cars.name = updateCarBrand
            }
            if (updateCarDescription != ""){
                cars.description = updateCarDescription
            }
            cars.image = pictureToDB(imageAuto.drawable)
            realm.commitTransaction()
        }
        dismiss()
        Toast.makeText(context, "Update success ", Toast.LENGTH_LONG).show()
    }


    //Находка для тегов!!!
    fun searchCarTags(carsList: ArrayList<String>, nameCar: String): ArrayList<String> {
        val newArrayListCar: ArrayList<String> = arrayListOf()
        val newArrayListCarRubbish: ArrayList<String> = arrayListOf()
        for (i in carsList){
            if (i.toLowerCase().indexOf(nameCar.toLowerCase())!= -1 && i.toLowerCase().indexOf(
                    nameCar.toLowerCase()
                ) == 0){
                newArrayListCar.add(i)
            }
            else if (i.toLowerCase().indexOf(nameCar.toLowerCase())!= -1){
                newArrayListCarRubbish.add(i)
            }
        }
        for (i in newArrayListCarRubbish){
            newArrayListCar.add(i)
        }


        return newArrayListCar
    }

    // находка картинок под теги
    fun searchCarPicture(carsList: ArrayList<String>, carName: String): Int{
        for ((c, i) in carsList.withIndex()){
            if ( i == carName){
                return c
            }
            else i.lastIndex
        }

        return carsList.size
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