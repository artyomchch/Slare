package tennisi.borzot.strada.fragments.add.domain

class DeleteCarItemUseCase(private val carListRepository: CarListRepository) {

    fun deleteCarItem(carItem: CarItem) {
        carListRepository.deleteCarItem(carItem)
    }
}