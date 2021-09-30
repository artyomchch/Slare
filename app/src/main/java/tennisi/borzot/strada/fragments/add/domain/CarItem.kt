package tennisi.borzot.strada.fragments.add.domain

data class CarItem(
    val name: String,
    val brand: String,
    val model: String,
    val description: String,
    var id: Int = UNDEFINED_ID
){
    companion object{
        const val UNDEFINED_ID = -1
    }
}
