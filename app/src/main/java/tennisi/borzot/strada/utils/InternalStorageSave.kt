package tennisi.borzot.strada.utils

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.firebase.ui.auth.AuthUI
import java.io.*

object InternalStorageSave {


     fun loadImageFromStorage(image: String): Bitmap {
        return try {
            val f = File(Constants.BASE_FOLDER + image + Constants.JPG_TYPE )
            Log.d("TAG", "loadImageFromStorage: $f")
            BitmapFactory.decodeStream(FileInputStream(f))
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            Bitmap.createBitmap(20, 20, Bitmap.Config.ARGB_8888)
        }

    }

     fun saveToInternalStorage(bitmapImage: Bitmap, name: String): String? {
        val cw = ContextWrapper(AuthUI.getApplicationContext())
        // path to /data/data/yourapp/app_data/imageDir
        val directory: File = cw.getDir(Constants.NAME_FOLDER_IMAGE, Context.MODE_PRIVATE)
        // Create imageDir
        val myPath = File(directory, "$name.jpg")
        Log.d("TAG", "saveToInternalStorage: $myPath")
        var fos: FileOutputStream? = null
        try {
            fos = FileOutputStream(myPath)
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                fos?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return directory.absolutePath
    }


}