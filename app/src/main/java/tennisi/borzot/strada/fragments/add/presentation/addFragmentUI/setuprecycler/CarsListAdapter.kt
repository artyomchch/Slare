package tennisi.borzot.strada.fragments.add.presentation.addFragmentUI.setuprecycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import tennisi.borzot.strada.databinding.ItemCarsBinding
import tennisi.borzot.strada.databinding.ItemCarsChoosenBinding
import tennisi.borzot.strada.fragments.add.domain.entity.CarItem

class CarsListAdapter : ListAdapter<CarItem, CarItemViewHolder>(CarItemDiffCallback()) {

    var onCarItemLongClickListener: ((CarItem) -> Unit)? = null
    var onCarItemClickListener: ((CarItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarItemViewHolder {

        return when (viewType) {
            VIEW_TYPE_ENABLED -> CarItemViewHolder.EnableViewHolder(
                ItemCarsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            VIEW_TYPE_CHOSEN -> CarItemViewHolder.ChosenViewHolder(
                ItemCarsChoosenBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> throw RuntimeException("Unknown view type: $viewType")
        }

    }

    override fun onBindViewHolder(viewHolder: CarItemViewHolder, position: Int) {
        val carsItem = getItem(position) ?: return
        when (viewHolder) {
            is CarItemViewHolder.EnableViewHolder -> {
                viewHolder.itemView.setOnClickListener {
                    onCarItemClickListener?.invoke(carsItem)

                }
                viewHolder.bind(carsItem)


            }
            is CarItemViewHolder.ChosenViewHolder -> {
                viewHolder.itemView.setOnClickListener {
                    onCarItemClickListener?.invoke(carsItem)
                }
                viewHolder.bind(carsItem)
            }


        }

    }


    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.enable) {
            VIEW_TYPE_CHOSEN
        } else {
            VIEW_TYPE_ENABLED
        }


    }

    companion object {
        const val VIEW_TYPE_ENABLED = 100
        const val VIEW_TYPE_CHOSEN = 101

        const val MAX_POOL_SIZE = 10
    }
}