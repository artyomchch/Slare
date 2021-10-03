package tennisi.borzot.strada.fragments.add.domain

class AddCarItemUseCase(private val carListRepository: CarListRepository) {

    fun addCarItem(carItem: CarItem){
        carListRepository.addCarItem(carItem)
    }

}