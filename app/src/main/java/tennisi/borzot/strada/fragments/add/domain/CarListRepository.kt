package tennisi.borzot.strada.fragments.add.domain

import androidx.lifecycle.LiveData

interface CarListRepository {

    fun editCarItem(carItem: CarItem)

    fun addCarItem(carItem: CarItem)

    fun getCarItem(carItemId: Int): CarItem

    fun deleteCarItem(carItem: CarItem)

    fun getCarList(): LiveData<List<CarItem>>

}