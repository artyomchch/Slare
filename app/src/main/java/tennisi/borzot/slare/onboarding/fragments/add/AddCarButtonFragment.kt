package tennisi.borzot.slare.onboarding.fragments.add



import android.annotation.SuppressLint
import android.graphics.Bitmap
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
import android.widget.Toast
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.core.graphics.drawable.toBitmap
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import io.realm.Realm
import io.realm.exceptions.RealmException
import kotlinx.android.synthetic.main.fragment_add_car.*
import kotlinx.android.synthetic.main.fragment_add_car.view.*
import me.gujun.android.taggroup.TagGroup
import tennisi.borzot.slare.R
import tennisi.borzot.slare.database.Cars
import tennisi.borzot.slare.strings.AppStrings
import java.io.ByteArrayOutputStream
import java.util.*


class AddCarButtonFragment(): BottomSheetDialogFragment() {

    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val appStrings: AppStrings = AppStrings()
        val rbLeftButton : AppCompatRadioButton
        val rbRightButton : AppCompatRadioButton
        val view = inflater.inflate(R.layout.fragment_add_car, container, false)
        val mTagGroup = view.findViewById(R.id.tag_group) as TagGroup
        // RadioButton
        var radioMode: String? = "auto"

        //realm
        var realm: Realm = Realm.getDefaultInstance()

        rbLeftButton = view.findViewById(R.id.rbLeft)
        rbRightButton = view.findViewById(R.id.rbRight)





        //tag Group
        mTagGroup.setTags(appStrings.cars)
        mTagGroup.setOnTagClickListener(object : TagGroup.OnTagClickListener {
            override fun onTagClick(tag: String?) {
                edit_car_brand.setText(tag)
                mTagGroup.setTags(arrayListOf())

            }
        })


        //EditText
        view.edit_car_brand.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                edit_car_brand.setSelection(p3)
            }

            override fun afterTextChanged(p0: Editable?) {
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

            }
        })






        //RadioButton
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
        view.save.setOnClickListener {
            addDBCar(realm, radioMode)
        }


        //cancel button
        view.cancel.setOnClickListener {
            dismiss()
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
//            val bitmap: Bitmap = imageAuto.drawable.toBitmap()
//            Log.d("DB", bitmap.toString())

          //  car.id = UUID.randomUUID().toString()
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

    fun searchCarPicture(carsList: ArrayList<String>, carName: String): Int{
        for ((c, i) in carsList.withIndex()){
            if ( i == carName){
                return c
            }
            else c
        }
        return carsList.size
    }

    private fun pictureToDB(image: Drawable): ByteArray{
        val bitmap = (image as BitmapDrawable).bitmap
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        val bitmapdata: ByteArray = stream.toByteArray()

        return bitmapdata
    }





}