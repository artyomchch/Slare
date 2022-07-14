package tennisi.borzot.strada.fragments.add.domain.usecases

import tennisi.borzot.strada.fragments.add.domain.repository.CarListRepository
import javax.inject.Inject

class ResetEnableFromCar @Inject constructor(private val carListRepository: CarListRepository) {

    suspend operator fun invoke(carItemId: Int) {
        carListRepository.resetEnableFromCar(carItemId)
    }

}