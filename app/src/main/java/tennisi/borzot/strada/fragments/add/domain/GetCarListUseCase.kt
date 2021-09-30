package tennisi.borzot.strada.fragments.add.domain

import androidx.lifecycle.LiveData

class GetCarListUseCase(private val carListRepository: CarListRepository) {

    fun getCarList(): LiveData<List<CarItem>> {
        return carListRepository.getCarList()
    }

}