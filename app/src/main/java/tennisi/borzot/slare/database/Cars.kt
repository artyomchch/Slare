package tennisi.borzot.slare.database

import android.graphics.Bitmap
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class Cars : RealmObject() {
    @PrimaryKey
    var id: String? = null
    var name: String? = null
    var description: String? = null
    var mode: String? = null
    var image: ByteArray? = null


}