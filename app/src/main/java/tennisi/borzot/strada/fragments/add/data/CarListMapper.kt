package tennisi.borzot.strada.fragments.add.data

import tennisi.borzot.strada.fragments.add.domain.entity.CarItem

class CarListMapper {

    fun mapEntityToDbModel(carItem: CarItem) = CarItemDbModel(
        id = carItem.id,
        name = carItem.name,
        brand = carItem.brand,
        model = carItem.model,
        enable = carItem.enable
    )

    fun mapDbModelToEntity(carItemDbModel: CarItemDbModel) = CarItem(
        id = carItemDbModel.id,
        name = carItemDbModel.name,
        brand = carItemDbModel.brand,
        model = carItemDbModel.model,
        enable = carItemDbModel.enable
    )

    fun mapListDbModelToListEntity(list: List<CarItemDbModel>) = list.map {
        mapDbModelToEntity(it)
    }
}