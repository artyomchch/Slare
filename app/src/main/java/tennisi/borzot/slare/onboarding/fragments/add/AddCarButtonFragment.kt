package tennisi.borzot.slare.onboarding.fragments.add

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cunoraz.tagview.Tag
import com.cunoraz.tagview.TagView
import com.cunoraz.tagview.TagView.OnTagClickListener
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_add_car.*
import kotlinx.android.synthetic.main.fragment_add_car.view.*
import tennisi.borzot.slare.R


class AddCarButtonFragment(): BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_car, container, false)
        val tagGroup :TagView
        var tagList: ArrayList<TagClass?>
        tagGroup = view.tag_group
        tagGroup.addTags(arrayOf("Tesla", "BMW", "KIA", "Mini", "Citroen", "Chevrolet", "Ferrari"))

        tagGroup.setOnTagClickListener(object : OnTagClickListener {
            override fun onTagClick(tag: Tag?, position: Int) {
                if (tag != null) {
                    editCarName.setText(tag.text)
                }
            }
        })

//        editCarName.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
//
//            }
//
//            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
//
//            }
//
//            override fun afterTextChanged(s: Editable) {
//
//            }
//        })


        fun setTags(cs: CharSequence){
            if (cs.toString().equals("")){
                tagGroup.addTags(ArrayList<Tag>())
                return
            }

            var text = cs.toString()
            val tags: ArrayList<Tag> = ArrayList()
            var tag: Tag



        }







        view.save_add_button.setOnClickListener {
            dismiss()
        }

        return view
    }






}