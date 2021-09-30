package tennisi.borzot.strada.fragments.add.domain

class GetCarItemUseCase(private val carListRepository: CarListRepository) {

    fun getCarItem(carItemId: Int): CarItem = carListRepository.getCarItem(carItemId)

}