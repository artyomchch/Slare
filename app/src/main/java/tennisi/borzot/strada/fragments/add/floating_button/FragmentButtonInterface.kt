package tennisi.borzot.strada.fragments.add.floating_button

import android.app.Application
import android.content.ClipDescription
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
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

    }

    interface Presenter{
        fun dataBaseAdd()
        fun dataBaseDelete(argument: Bundle)


    }

    interface Model{
        fun addDataToDB(brand: String, model: String, description: String, imageCar: Drawable):Boolean
        fun deleteDataToDB()
        fun restoreData(argument: Bundle)

    }

}