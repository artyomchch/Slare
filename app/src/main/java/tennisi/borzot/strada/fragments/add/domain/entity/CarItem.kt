package tennisi.borzot.strada.fragments.add.domain.entity

data class CarItem(
    val brand: String,
    val model: String,
    val profile: String,
    val pathToPic: String,
    val enable: Boolean,
    var id: Int = UNDEFINED_ID

){
    companion object{
        const val UNDEFINED_ID = 0
    }
}
