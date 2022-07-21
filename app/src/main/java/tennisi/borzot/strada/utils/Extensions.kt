package tennisi.borzot.strada.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import tennisi.borzot.strada.R
import tennisi.borzot.strada.fragments.add.presentation.carItemUI.CarItemFragment
import tennisi.borzot.strada.utils.InternalStorageSave.loadImageFromStorage


fun ShapeableImageView.setImage(context: Context, url: String){
    Glide.with(context)
        .asBitmap()
        .load(loadImageFromStorage(url))
   //     .placeholder(R.drawable.no_auto)
        .centerCrop()
        .into(this)
}

fun String.splitDataUniqueId() = this.split("/").last()

fun ImageView.setImage(context: Context, url: String){
    Glide.with(context)
        .asBitmap()
        .load(loadImageFromStorage(url))
        .placeholder(R.drawable.no_auto)
        .centerCrop()
        .into(this)
}



