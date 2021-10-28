package tennisi.borzot.strada.fragments.add.domain.usecases

import tennisi.borzot.strada.fragments.add.domain.repository.CarListRepository

class GetCarItemUseCase(private val carListRepository: CarListRepository) {

    suspend operator fun invoke(carItemId: Int) = carListRepository.getCarItem(carItemId)

}