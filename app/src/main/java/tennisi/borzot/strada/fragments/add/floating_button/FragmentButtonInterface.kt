package tennisi.borzot.strada.fragments.add.floating_button

import android.app.Application
import android.content.ClipDescription
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Binder
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.viewpager.widget.ViewPager
import tennisi.borzot.strada.onboarding.MVPOnBoarding.OnBoardingData

interface FragmentButtonInterface {
    interface View{
        fun getBrand(): String
        fun getModel():String
        fun getDescription(): String
        fun getImageCar(): Drawable
        fun getArgument(): Bundle
        fun logAddDb()
        fun logDeleteDB()
        fun closeFragment()

    }

    interface Presenter{
        fun dataBaseAdd()
        fun dataBaseDelete()
        fun dataBaseUpdate()
        fun setCarId():String
        fun setCarBrand():String
        fun setCarModel():String
        fun setCarDescription():String
        fun setCarPicture():Bitmap
        fun getUpdateCarBrand(updateBrand: String)
        fun getUpdateCarModel(updateModel: String)
        fun getUpdateCarDescription(updateDescription: String)
        fun getRemake():Boolean
        fun setTagCars(): ArrayList<String>
        fun searchInputTags(inputCar: String): ArrayList<String>
        fun searchInputPicture(inputCar: String): Int
    }

    interface Model{
        fun addDataToDB(brand: String, model: String, description: String, imageCar: Drawable):Boolean
        fun deleteDataToDB()
        fun dataBaseUpdate(imageCar: Drawable)
        fun restoreData(argument: Bundle)
        fun setCarId():String
        fun setCarBrand():String
        fun setCarModel():String
        fun setCarDescription():String
        fun getUpdateCarBrand(updateBrand: String)
        fun getUpdateCarModel(updateModel: String)
        fun getUpdateCarDescription(updateDescription: String)
        fun setCarPicture():Bitmap
        fun getCarChoose():Boolean
        fun getCarTag(): ArrayList<String>
        fun searchInputTags(inputCar: String): ArrayList<String>
        fun searchInputPicture(inputCar: String): Int

    }

}