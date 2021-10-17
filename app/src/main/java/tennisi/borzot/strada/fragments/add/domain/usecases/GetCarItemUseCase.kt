package tennisi.borzot.strada.fragments.add.domain.usecases

import tennisi.borzot.strada.fragments.add.domain.entity.CarItem
import tennisi.borzot.strada.fragments.add.domain.repository.CarListRepository

class GetCarItemUseCase(private val carListRepository: CarListRepository) {

    fun getCarItem(carItemId: Int): CarItem = carListRepository.getCarItem(carItemId)

}