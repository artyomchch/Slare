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
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_add_car.*
import kotlinx.android.synthetic.main.fragment_add_car.view.*
import me.gujun.android.taggroup.TagGroup
import tennisi.borzot.strada.R
import tennisi.borzot.strada.database.Cars
import tennisi.borzot.strada.strings.AppStrings
import java.io.ByteArrayOutputStream
import java.util.*


class AddCarButtonFragment(): BottomSheetDialogFragment(), FragmentButtonInterface.View {
    private var presenter: FragmentButtonPresenter? = null

    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        presenter = FragmentButtonPresenter(this)


        val appStrings = AppStrings()
        val rbLeftButton : AppCompatRadioButton
        val rbRightButton : AppCompatRadioButton
        val view = inflater.inflate(R.layout.fragment_add_car, container, false)


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




        if (presenter!!.getRemake()){
            carDelete.visibility = View.VISIBLE
            carCancel.visibility =  View.GONE
            carReCancel.visibility = View.VISIBLE
            carSave.visibility = View.GONE
            carEditBrand.setText(presenter?.setCarBrand())
            carEditName.setText(presenter?.setCarModel())
            carImage.setImageBitmap(presenter!!.setCarPicture())
        }
        else {
            carDelete.visibility = View.GONE
            carCancel.visibility = View.VISIBLE
        }






        //Слушатель на тэги
        mTagGroup.setTags(presenter!!.setTagCars())
        mTagGroup.setOnTagClickListener(object : TagGroup.OnTagClickListener {
            override fun onTagClick(tag: String?) {
                carEditBrand.setText(tag)
                carEditBrand.setSelection(tag.toString().length)
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
                mTagGroup.setTags(presenter!!.searchInputTags(p0.toString()))
                imageAuto.setImageResource(presenter!!.searchInputPicture(p0.toString()))

                if (mTagGroup.tags.isNotEmpty()) {
                    if (p0.toString().contains(mTagGroup.tags[0])) {
                        tag_group.visibility = View.GONE
                    } else tag_group.visibility = View.VISIBLE
                } else tag_group.visibility = View.GONE

                //works with button view
                if (presenter!!.getRemake()){
                    if (p0.toString() != presenter!!.setCarBrand()){
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
                if (presenter!!.getRemake()){
                    if (p0.toString() != presenter!!.setCarModel()){
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
            presenter?.dataBaseAdd()
        }


        //cancel button
        carCancel.setOnClickListener {
            dismiss()
        }

        //delete button
        carDelete.setOnClickListener {
            deleteCar(presenter!!.setCarId(), realm)
        }

        //another cancel button
        carReCancel.setOnClickListener {
            dismiss()
        }

        //update button
        carUpdate.setOnClickListener {
            updateCar(presenter!!.setCarId(), realm, updateCarBrand, updateCarDescription)
        }





        return view
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






    // перевод найденных картинок в байткод
    private fun pictureToDB(image: Drawable): ByteArray{
        val bitmap = (image as BitmapDrawable).bitmap
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        val bitmapdata: ByteArray = stream.toByteArray()
        return bitmapdata
    }





    override fun getBrand(): String = edit_car_brand.text.toString()

    override fun getModel(): String = edit_car_name.text.toString()

    override fun getDescription(): String = ""

    override fun getImageCar(): Drawable = imageAuto.drawable

    override fun getArgument(): Bundle = arguments!!




    override fun logAddDb() {
        Log.d("text works", "logAddDb: ")
        Toast.makeText(context, "Realm works: Upload car", Toast.LENGTH_LONG).show()
    }

    override fun logDeleteDB() {
        Toast.makeText(context, "Delete success ", Toast.LENGTH_LONG).show()
    }

    override fun closeFragment() {
        dismiss()
    }


}