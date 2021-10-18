package tennisi.borzot.strada.fragments.add.domain.usecases

import tennisi.borzot.strada.fragments.add.domain.entity.CarItem
import tennisi.borzot.strada.fragments.add.domain.repository.CarListRepository

class AddCarItemUseCase(private val carListRepository: CarListRepository) {

    operator fun invoke(carItem: CarItem) {
        carListRepository.addCarItem(carItem)
    }

}