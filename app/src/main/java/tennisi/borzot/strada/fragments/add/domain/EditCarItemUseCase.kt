package tennisi.borzot.strada.fragments.add.domain

class EditCarItemUseCase(private val carListRepository: CarListRepository) {

    fun editCarItem(carItemId: CarItem) {
        carListRepository.editCarItem(carItemId)
    }
}