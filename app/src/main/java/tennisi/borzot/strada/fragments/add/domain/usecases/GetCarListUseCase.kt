package tennisi.borzot.strada.fragments.add.domain.usecases

import androidx.lifecycle.LiveData
import tennisi.borzot.strada.fragments.add.domain.entity.CarItem
import tennisi.borzot.strada.fragments.add.domain.repository.CarListRepository

class GetCarListUseCase(private val carListRepository: CarListRepository) {

    operator fun invoke():LiveData<List<CarItem>> = carListRepository.getCarList()

}