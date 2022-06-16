package tennisi.borzot.strada.fragments.add.data.mapper

import tennisi.borzot.strada.fragments.add.data.database.CarItemDbModel
import tennisi.borzot.strada.fragments.add.domain.entity.CarItem
import javax.inject.Inject

class CarListMapper @Inject constructor() {

    fun mapEntityToDbModel(carItem: CarItem) = CarItemDbModel(
        id = carItem.id,
        name = carItem.name,
        brand = carItem.brand,
        model = carItem.model,
        pathToPic =  carItem.pathToPic,
        enable = carItem.enable
    )

    fun mapDbModelToEntity(carItemDbModel: CarItemDbModel) = CarItem(
        id = carItemDbModel.id,
        name = carItemDbModel.name,
        brand = carItemDbModel.brand,
        model = carItemDbModel.model,
        pathToPic =  carItemDbModel.pathToPic,
        enable = carItemDbModel.enable
    )

    fun mapListDbModelToListEntity(list: List<CarItemDbModel>) = list.map {
        mapDbModelToEntity(it)
    }
}