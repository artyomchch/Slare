package tennisi.borzot.strada.fragments.add.presentation

import androidx.recyclerview.widget.DiffUtil
import tennisi.borzot.strada.fragments.add.domain.CarItem

class CarItemDiffCallback : DiffUtil.ItemCallback<CarItem>() {
    override fun areItemsTheSame(oldItem: CarItem, newItem: CarItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CarItem, newItem: CarItem): Boolean {
        return oldItem == newItem
    }
}