package tennisi.borzot.strada.fragments.add.presentation.addFragmentUI

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import tennisi.borzot.strada.R
import tennisi.borzot.strada.fragments.add.domain.CarItem

class CarsListAdapter : ListAdapter<CarItem, CarItemViewHolder>(CarItemDiffCallback()) {

    var onCarItemLongClickListener: ((CarItem) -> Unit)? = null
    var onCarItemClickListener: ((CarItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarItemViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_DISABLED -> R.layout.item_cars_disable
            VIEW_TYPE_ENABLED -> R.layout.item_cars
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return CarItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: CarItemViewHolder, position: Int) {
        val carsItem = getItem(position)

        viewHolder.nameOfCar.text = carsItem.name
        viewHolder.brandOfCar.text = carsItem.brand
        viewHolder.modelOfCar.text = carsItem.model
        viewHolder.itemView.setOnLongClickListener {
            onCarItemLongClickListener?.invoke(carsItem)
            true
        }
        viewHolder.itemView.setOnClickListener {
            onCarItemClickListener?.invoke(carsItem)
        }

    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.enable) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }

    companion object {
        const val VIEW_TYPE_ENABLED = 100
        const val VIEW_TYPE_DISABLED = 101

        const val MAX_POOL_SIZE = 10
    }
}