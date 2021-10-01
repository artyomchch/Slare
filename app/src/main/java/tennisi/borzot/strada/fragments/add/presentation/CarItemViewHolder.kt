package tennisi.borzot.strada.fragments.add.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tennisi.borzot.strada.R

class CarItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val nameOfCar: TextView = view.findViewById(R.id.text_name_of_car)
    val brandOfCar: TextView = view.findViewById(R.id.text_brand_of_car)
    val modelOfCar: TextView = view.findViewById(R.id.text_model_of_car)
}