package tennisi.borzot.strada.fragments.add.floating_button

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import tennisi.borzot.strada.databinding.FragmentAddCarBinding

class AddCarButtonFragment: BottomSheetDialogFragment(), FragmentButtonInterface.View {
    private var presenter: FragmentButtonPresenter? = null
    private lateinit var binding: FragmentAddCarBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddCarBinding.inflate(inflater, container, false)
        presenter = FragmentButtonPresenter(this)

        if (presenter!!.getRemake()){
            binding.delete.visibility = View.VISIBLE
            binding.cancel.visibility =  View.GONE
            binding.carCancel.visibility = View.VISIBLE
            binding.save.visibility = View.GONE
            binding.editCarBrand.setText(presenter?.setCarBrand())
            binding.editCarModel.setText(presenter?.setCarModel())
            binding.editCarDescription.setText(presenter?.setCarDescription())
            binding.imageAuto.setImageBitmap(presenter!!.setCarPicture())
        }
        else {
            binding.delete.visibility = View.GONE
            binding.cancel.visibility = View.VISIBLE
        }

        //Слушатель на тэги
        binding.tagGroup.setTags(presenter!!.setTagCars())
        binding.tagGroup.setOnTagClickListener { tag ->
            binding.editCarBrand.setText(tag)
            binding.editCarBrand.setSelection(tag.toString().length)
        }

        //слушатель на EditText Brand Edit
        binding.editCarBrand.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {

                //work with tags
                binding.tagGroup.setTags(presenter!!.searchInputTags(p0.toString()))
                binding.imageAuto.setImageResource(presenter!!.searchInputPicture(p0.toString()))

                if (binding.tagGroup.tags.isNotEmpty()) {
                    if (p0.toString().contains(binding.tagGroup.tags[0])) {
                        binding.tagGroup.visibility = View.GONE
                    } else binding.tagGroup.visibility = View.VISIBLE
                } else binding.tagGroup.visibility = View.GONE

                //works with button view
                if (presenter!!.getRemake()){
                    if (p0.toString() != presenter!!.setCarBrand()){
                        binding.carCancel.visibility = View.GONE
                        binding.update.visibility = View.VISIBLE
                        presenter!!.getUpdateCarBrand(p0.toString())
                    }
                    else {
                        binding.update.visibility = View.GONE
                        binding.carCancel.visibility = View.VISIBLE
                        presenter!!.getUpdateCarBrand("")
                    }
                }


            }
        })


        binding.editCarModel.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                if (presenter!!.getRemake()){
                    if (p0.toString() != presenter!!.setCarModel()){
                        binding.carCancel.visibility = View.GONE
                        binding.update.visibility = View.VISIBLE
                        presenter!!.getUpdateCarModel(p0.toString())

                    }
                    else {
                        binding.update.visibility = View.GONE
                        binding.carCancel.visibility = View.VISIBLE
                        presenter!!.getUpdateCarModel("")
                    }
                }
            }

        })

        binding.editCarDescription.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if (presenter!!.getRemake()){
                    if (p0.toString() != presenter!!.setCarDescription()){
                        binding.carCancel.visibility = View.GONE
                        binding.update.visibility = View.VISIBLE
                        presenter!!.getUpdateCarDescription(p0.toString())

                    }
                    else {
                        binding.update.visibility = View.GONE
                        binding.carCancel.visibility = View.VISIBLE
                        presenter!!.getUpdateCarDescription("")
                    }
                }

            }

        })

        //Слушатель на RadioButton
        binding.rbLeft.setOnClickListener {
                binding.rbLeft.setTextColor(Color.WHITE)
                binding.rbRight.setTextColor(Color.parseColor("#77B2D8"))
        }

        binding.rbRight.setOnClickListener{
            binding.rbLeft.setTextColor(Color.parseColor("#77B2D8"))
            binding.rbRight.setTextColor(Color.WHITE)
        }

        //save button
        binding.save.setOnClickListener {
            presenter?.dataBaseAdd()
        }

        //cancel button
        binding.delete.setOnClickListener {
            closeFragment()
        }

        //delete button
        binding.delete.setOnClickListener {
            presenter!!.dataBaseDelete()
        }

        //another cancel button
        binding.carCancel.setOnClickListener {
            closeFragment()
        }

        //update button
        binding.update.setOnClickListener {
            presenter!!.dataBaseUpdate()
        }

        return binding.root
    }




    override fun getBrand(): String = binding.editCarBrand.text.toString()

    override fun getModel(): String = binding.editCarModel.text.toString()

    override fun getDescription(): String = binding.editCarDescription.text.toString()

    override fun getImageCar(): Drawable = binding.imageAuto.drawable

    override fun getArgument(): Bundle? = arguments

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