package tennisi.borzot.strada.fragments.add.presentation.addFragmentUI.setuprecycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import tennisi.borzot.strada.R
import tennisi.borzot.strada.databinding.ItemCarsBinding
import tennisi.borzot.strada.fragments.add.domain.entity.CarItem

class CarsListAdapter : ListAdapter<CarItem, CarItemViewHolder>(CarItemDiffCallback()) {

    var onCarItemLongClickListener: ((CarItem) -> Unit)? = null
    var onCarItemClickListener: ((CarItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarItemViewHolder {

        val binding = ItemCarsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarItemViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: CarItemViewHolder, position: Int) {
        val carsItem = getItem(position) ?: return
        with(viewHolder.binding) {
            textBrandOfCar.text = carsItem.brand
            textModelOfCar.text = carsItem.model
            nameProfile.text = carsItem.name
            Glide.with(root)
                .load(carsItem.pathToPic)
                .placeholder(R.drawable.no_auto)
                .centerCrop()
                .into(imageUrl)
            root.setOnLongClickListener {
                onCarItemLongClickListener?.invoke(carsItem)
                true
            }
            root.setOnClickListener {
                onCarItemClickListener?.invoke(carsItem)
            }

        }


    }



    companion object {
        const val VIEW_TYPE_ENABLED = 100


        const val MAX_POOL_SIZE = 10
    }
}