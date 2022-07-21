package tennisi.borzot.strada.fragments.add.presentation.addFragmentUI.setuprecycler


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.stfalcon.imageviewer.StfalconImageViewer
import tennisi.borzot.strada.R
import tennisi.borzot.strada.databinding.ItemCarsBinding
import tennisi.borzot.strada.databinding.ItemCarsChoosenBinding
import tennisi.borzot.strada.fragments.add.domain.entity.CarItem
import tennisi.borzot.strada.utils.setImage
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException


sealed class CarItemViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {


    class EnableViewHolder(private val binding: ItemCarsBinding) : CarItemViewHolder(binding) {

        fun bind(carsItem: CarItem) {
            with(binding) {
                textBrandOfCar.text = carsItem.brand
                textModelOfCar.text = carsItem.model
                nameProfile.text = carsItem.profile
//                Glide.with(root)
//                    .asBitmap()
//                    .load(loadImageFromStorage(carsItem.pathToPic))
//                    .placeholder(R.drawable.no_auto)
//                    .centerCrop()
//                    .into(imageUrl)

                imageUrl.setImage(root.context, carsItem.pathToPic)
            }

            binding.imageUrl.setOnClickListener {
                StfalconImageViewer.Builder(binding.imageUrl.context, listOf(carsItem)) { view, image ->
                    Glide.with(binding.root)
                        .asBitmap()
                        .load(loadImageFromStorage(carsItem.pathToPic))
                        .placeholder(R.drawable.ic_app_icon)
                        .into(view)
                //    view.setImage(binding.root.context, carsItem.pathToPic)
                }.withBackgroundColorResource(R.color.colorPicture)
                    .show()
            }
        }


    }

    class ChosenViewHolder(private val binding: ItemCarsChoosenBinding) : CarItemViewHolder(binding) {
        fun bind(carsItem: CarItem) {
            with(binding) {
                textBrandOfCar.text = carsItem.brand
                textModelOfCar.text = carsItem.model
                nameProfile.text = carsItem.profile
//                Glide.with(root)
//                    .asBitmap()
//                    .load(loadImageFromStorage(carsItem.pathToPic))
//                    .placeholder(R.drawable.no_auto)
//                    .centerCrop()
//                    .into(imageUrl)
                imageUrl.setImage(root.context, carsItem.pathToPic)
                Log.d("TAG", "bind: ${loadImageFromStorage(carsItem.pathToPic)}")

            }

            binding.imageUrl.setOnClickListener {
                StfalconImageViewer.Builder(binding.imageUrl.context, listOf(carsItem)) { view, _ ->
                    Glide.with(binding.root)
                        .asBitmap()
                        .load(loadImageFromStorage(carsItem.pathToPic))
                        .placeholder(R.drawable.ic_app_icon)
                        .into(view)
               //     view.setImage(binding.root.context, carsItem.pathToPic)


                }.withBackgroundColorResource(R.color.colorPicture)
                    .show()
            }


        }
    }

    companion object {
        private fun loadImageFromStorage(image: String): Bitmap {
            return try {
                val f = File("/data/user/0/tennisi.borzot.slare/app_user_car_image/$image.jpg" )
                Log.d("TAG", "loadImageFromStorage: $f")
                BitmapFactory.decodeStream(FileInputStream(f))
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
                Bitmap.createBitmap(20, 20, Bitmap.Config.ARGB_8888)
            }

        }
    }

}