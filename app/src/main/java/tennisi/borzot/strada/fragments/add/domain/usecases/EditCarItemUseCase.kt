package tennisi.borzot.strada.fragments.add.domain.usecases

import tennisi.borzot.strada.fragments.add.domain.entity.CarItem
import tennisi.borzot.strada.fragments.add.domain.repository.CarListRepository

class EditCarItemUseCase(private val carListRepository: CarListRepository) {

    fun editCarItem(carItemId: CarItem) {
        carListRepository.editCarItem(carItemId)
    }
}