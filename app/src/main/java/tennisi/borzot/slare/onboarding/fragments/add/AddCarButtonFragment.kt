package tennisi.borzot.slare.onboarding.fragments.add

//import com.cunoraz.tagview.Tag
//import com.cunoraz.tagview.TagView
//import com.cunoraz.tagview.TagView.OnTagClickListener

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatRadioButton
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_add_car.view.*
import me.gujun.android.taggroup.TagGroup
import tennisi.borzot.slare.R


class AddCarButtonFragment(): BottomSheetDialogFragment() {

    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rbLeftButton : AppCompatRadioButton
        val rbRightButton : AppCompatRadioButton
        val view = inflater.inflate(R.layout.fragment_add_car, container, false)
       // val tagCar :TagView
        var tagList: ArrayList<TagClass?>
      //  tagCar = view.tag_group_car
     //   tagCar.addTags(arrayOf("Tesla", "BMW", "KIA", "Mini", "Citroen", "Chevrolet", "Ferrari"))
        val mTagGroup = view.findViewById(R.id.tag_group) as TagGroup
        mTagGroup.setTags(*arrayOf("Tesla", "BMW", "KIA", "Mini", "Citroen", "Chevrolet", "Ferrari"))


        rbLeftButton = view.findViewById(R.id.rbLeft)
        rbRightButton = view.findViewById(R.id.rbRight)


        rbLeftButton.setOnClickListener {
                view.rbLeft.setTextColor(Color.WHITE)
                view.rbRight.setTextColor(Color.parseColor("#77B2D8"))
        }

        rbRightButton.setOnClickListener{
                view.rbLeft.setTextColor(Color.parseColor("#77B2D8"))
                view.rbRight.setTextColor(Color.WHITE)
        }




        view.cansel.setOnClickListener {
            dismiss()
        }

        return view
    }






}