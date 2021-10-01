package tennisi.borzot.strada.fragments.add.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tennisi.borzot.strada.R
import tennisi.borzot.strada.fragments.add.domain.CarItem

class CarsListAdapter : RecyclerView.Adapter<CarsListAdapter.CarsItemViewHolder>() {

    var carsList = listOf<CarItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarsItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cars, parent, false)
        return CarsItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: CarsItemViewHolder, position: Int) {
        val carsItem = carsList[position]

        viewHolder.nameOfCar.text = carsItem.name
        viewHolder.brandOfCar.text = carsItem.brand
        viewHolder.modelOfCar.text = carsItem.model

    }

    override fun getItemCount(): Int {
        return carsList.size
    }

    class CarsItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val nameOfCar: TextView = view.findViewById(R.id.text_name_of_car)
        val brandOfCar: TextView = view.findViewById(R.id.text_brand_of_car)
        val modelOfCar: TextView = view.findViewById(R.id.text_model_of_car)

    }
}