package tennisi.borzot.strada.fragments.add.listviewcars

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import tennisi.borzot.strada.R
import tennisi.borzot.strada.database.Cars


@Suppress("DEPRECATION")
class AdapterCar(var mCtx: Context, var resources: Int, var items: List<Cars>)
    : ArrayAdapter<Cars>(mCtx, resources, items) {
    @SuppressLint("UseCompatLoadingForDrawables", "ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(resources, null)

        val imageView: ImageView = view.findViewById(R.id.imageViewBrand)
        val brandText: TextView = view.findViewById(R.id.brandCarWriter)
        val modelText: TextView = view.findViewById(R.id.modelCarWriter)
        val descriptionCar: TextView = view.findViewById(R.id.descriptionCarWriter)
        //val modeText: TextView = view.findViewById(R.id.modeCarWriter)


        val mItem: Cars = items[position]
        imageView.setImageBitmap(mItem.image?.let { BitmapFactory.decodeByteArray(mItem.image, 0 , it.size) })
        brandText.text = mItem.carBrand
        modelText.text = mItem.carModel
        descriptionCar.text = mItem.carDescription
     //   modeText.text = mItem.mode

        return view
    }
}