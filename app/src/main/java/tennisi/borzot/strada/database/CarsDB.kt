package tennisi.borzot.strada.database

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class CarsDB : RealmObject() {
    @PrimaryKey
    var id: String? = null
    var carBrand: String? = null
    var carModel: String? = null
    var carDescription: String? = null
    var mode: String? = null
    var image: ByteArray? = null


}