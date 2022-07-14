package tennisi.borzot.strada.fragments.add.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import tennisi.borzot.strada.fragments.add.data.database.CarConfigDao
import tennisi.borzot.strada.fragments.add.data.database.CarListDao
import tennisi.borzot.strada.fragments.add.data.mapper.CarListMapper
import tennisi.borzot.strada.fragments.add.domain.entity.CarItem
import tennisi.borzot.strada.fragments.add.domain.entity.SoundItem
import tennisi.borzot.strada.fragments.add.domain.repository.CarListRepository
import javax.inject.Inject

class CarListRepositoryImpl @Inject constructor(
    private val mapper: CarListMapper,
    private val carListDao: CarListDao,
    private val carConfigDao: CarConfigDao
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

    override suspend fun resetEnableFromCar(carItemId: Int) {
        carListDao.resetEnableFromCar(carItemId)
    }

    override suspend fun resetEnableFromCarWithAddCar() {
        carListDao.resetEnableFromCarWithAddNew()
    }

    override fun getCarList(): LiveData<List<CarItem>> = Transformations.map(carListDao.getCarList()) {
        mapper.mapListDbModelToListEntity(it)
    }

    override fun getConfigListById(carItemId: Int): LiveData<List<SoundItem>> = Transformations.map(carConfigDao.getSoundAndConfig(carItemId)){ aggregatedData ->
        mapper.mapListDbModelSoundToListEntity(aggregatedData.entries.map { it.key })
    }


}