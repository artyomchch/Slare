package tennisi.borzot.strada.fragments.add.presentation.addFragmentUI.setuprecycler

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.stfalcon.imageviewer.StfalconImageViewer
import tennisi.borzot.strada.R
import tennisi.borzot.strada.databinding.ItemCarsBinding
import tennisi.borzot.strada.databinding.ItemCarsChoosenBinding
import tennisi.borzot.strada.fragments.add.domain.entity.CarItem

sealed class CarItemViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {



    class EnableViewHolder(private val binding: ItemCarsBinding) : CarItemViewHolder(binding) {

        fun bind(carsItem: CarItem) {
            with(binding) {
                textBrandOfCar.text = carsItem.brand
                textModelOfCar.text = carsItem.model
                nameProfile.text = carsItem.profile
                Glide.with(root)
                    .load(carsItem.pathToPic)
                    .placeholder(R.drawable.no_auto)
                    .centerCrop()
                    .into(imageUrl)

            }

            binding.imageUrl.setOnClickListener {
                StfalconImageViewer.Builder(binding.imageUrl.context, listOf(carsItem)) { view, image ->
                    Glide.with(binding.root)
                        .load(image.pathToPic)
                        .placeholder(R.drawable.ic_app_icon)
                        .into(view)
                }.show()
            }
        }



    }

    class ChosenViewHolder(private val binding: ItemCarsChoosenBinding) : CarItemViewHolder(binding) {
        fun bind(carsItem: CarItem) {
            with(binding) {
                textBrandOfCar.text = carsItem.brand
                textModelOfCar.text = carsItem.model
                nameProfile.text = carsItem.profile
                Glide.with(root)
                    .load(carsItem.pathToPic)
                    .placeholder(R.drawable.no_auto)
                    .centerCrop()
                    .into(imageUrl)


            }

            binding.imageUrl.setOnClickListener {
                StfalconImageViewer.Builder(binding.imageUrl.context, listOf(carsItem)) { view, image ->
                    Glide.with(binding.root)
                        .load(image.pathToPic)
                        .placeholder(R.drawable.ic_app_icon)
                        .into(view)
                }.show()
            }


        }
    }

}