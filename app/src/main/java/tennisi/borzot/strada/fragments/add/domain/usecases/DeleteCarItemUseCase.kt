package tennisi.borzot.strada.fragments.add.domain.usecases

import tennisi.borzot.strada.fragments.add.domain.entity.CarItem
import tennisi.borzot.strada.fragments.add.domain.repository.CarListRepository
import javax.inject.Inject

class DeleteCarItemUseCase @Inject constructor(private val carListRepository: CarListRepository) {

    suspend operator fun invoke(carItem: CarItem) {
        carListRepository.deleteCarItem(carItem)
    }

}