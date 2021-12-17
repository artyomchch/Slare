package tennisi.borzot.strada.fragments.add.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import tennisi.borzot.strada.fragments.add.data.database.AppDatabase
import tennisi.borzot.strada.fragments.add.data.mapper.CarListMapper
import tennisi.borzot.strada.fragments.add.domain.entity.CarItem
import tennisi.borzot.strada.fragments.add.domain.repository.CarListRepository
import javax.inject.Inject

class CarListRepositoryImpl @Inject constructor(
    context: Context,
    private val mapper: CarListMapper
) : CarListRepository {

    private val carListDao = AppDatabase.getInstance(context).carListDao()
  //  private val mapper = CarListMapper()

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