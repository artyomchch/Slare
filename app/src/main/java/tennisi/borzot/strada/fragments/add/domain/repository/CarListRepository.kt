package tennisi.borzot.strada.fragments.add.domain.repository

import androidx.lifecycle.LiveData
import tennisi.borzot.strada.fragments.add.domain.entity.CarItem

interface CarListRepository {

    fun editCarItem(carItem: CarItem)

    fun addCarItem(carItem: CarItem)

    fun getCarItem(carItemId: Int): CarItem

    fun deleteCarItem(carItem: CarItem)

    fun getCarList(): LiveData<List<CarItem>>

}