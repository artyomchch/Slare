package tennisi.borzot.strada.fragments.add.domain.usecases

import tennisi.borzot.strada.fragments.add.domain.entity.CarItem
import tennisi.borzot.strada.fragments.add.domain.repository.CarListRepository

class DeleteCarItemUseCase(private val carListRepository: CarListRepository) {

    fun deleteCarItem(carItem: CarItem) {
        carListRepository.deleteCarItem(carItem)
    }
}