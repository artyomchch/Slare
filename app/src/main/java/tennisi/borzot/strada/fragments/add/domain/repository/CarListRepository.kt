package tennisi.borzot.strada.fragments.add.domain.repository

import androidx.lifecycle.LiveData
import tennisi.borzot.strada.fragments.add.domain.entity.CarItem
import tennisi.borzot.strada.fragments.add.domain.entity.SoundItem

interface CarListRepository {

    suspend fun editCarItem(carItem: CarItem)

    suspend fun addCarItem(carItem: CarItem)

    suspend fun getCarItem(carItemId: Int): CarItem

    suspend fun deleteCarItem(carItem: CarItem)

    fun getCarList(): LiveData<List<CarItem>>

    fun getConfigListById(carItemId: Int): LiveData<List<SoundItem>>

}