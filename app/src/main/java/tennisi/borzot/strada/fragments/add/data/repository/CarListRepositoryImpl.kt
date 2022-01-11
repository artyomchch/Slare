package tennisi.borzot.strada.fragments.add.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import tennisi.borzot.strada.fragments.add.data.database.CarListDao
import tennisi.borzot.strada.fragments.add.data.mapper.CarListMapper
import tennisi.borzot.strada.fragments.add.domain.entity.CarItem
import tennisi.borzot.strada.fragments.add.domain.repository.CarListRepository
import javax.inject.Inject

class CarListRepositoryImpl @Inject constructor(
    private val mapper: CarListMapper,
    private val carListDao: CarListDao
) : CarListRepository {


    override suspend fun editCarItem(carItem: CarItem) {
        carListDao.addCarItem(mapper.mapEntityToDbModel(carItem))
    }

    override suspend fun addCarItem(carItem: CarItem) {
        carListDao.addCarItem(mapper.mapEntityToDbModel(carItem))
    }

    override suspend fun getCarItem(carItemId: Int): CarItem {
        val dbModel = carListDao.getCarItem(carItemId)
        return mapper.mapDbModelToEntity(dbModel)
    }

    override suspend fun deleteCarItem(carItem: CarItem) {
        carListDao.deleteCarItem(carItem.id)
    }

    override fun getCarList(): LiveData<List<CarItem>> = Transformations.map(carListDao.getCarList()) {
        mapper.mapListDbModelToListEntity(it)
    }


}