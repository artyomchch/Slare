package tennisi.borzot.slare.onboarding.fragments.add.listviewcars

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.cars_list.view.*
import tennisi.borzot.slare.R


@Suppress("DEPRECATION")
class AdapterCar(var mCtx: Context, var resources: Int, var items: List<ModelCars>)
    : ArrayAdapter<ModelCars>(mCtx, resources, items) {
    @SuppressLint("UseCompatLoadingForDrawables", "ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(resources, null)

        val imageView: ImageView = view.findViewById(R.id.imageViewBrand)
        val brandText: TextView = view.findViewById(R.id.brandCarWriter)
        val modeText: TextView = view.findViewById(R.id.modeCarWriter)
        val descriptionCar: TextView = view.findViewById(R.id.descriptionCarWriter)

        var mItem: ModelCars = items[position]
        imageView.setImageBitmap(mItem.img?.let { BitmapFactory.decodeByteArray(mItem.img, 0 , it.size) })
        brandText.text = mItem.brand
        descriptionCar.text = mItem.description
        modeText.text = mItem.mode


        return view
    }
}