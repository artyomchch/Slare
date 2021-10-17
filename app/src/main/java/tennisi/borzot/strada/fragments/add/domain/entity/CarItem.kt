package tennisi.borzot.strada.fragments.add.domain.entity

data class CarItem(
    val name: String,
    val brand: String,
    val model: String,
    val enable: Boolean,
    var id: Int = UNDEFINED_ID
){
    companion object{
        const val UNDEFINED_ID = -1
    }
}
