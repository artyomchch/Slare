package tennisi.borzot.strada.fragments.add.data.mapper

import tennisi.borzot.strada.fragments.add.data.database.models.CarItemDbModel
import tennisi.borzot.strada.fragments.add.data.database.models.SoundDbModel
import tennisi.borzot.strada.fragments.add.domain.entity.CarItem
import tennisi.borzot.strada.fragments.add.domain.entity.SoundItem
import javax.inject.Inject

class CarListMapper @Inject constructor() {

    fun mapEntityToDbModel(carItem: CarItem) = CarItemDbModel(
        id = carItem.id,
        profile = carItem.profile,
        brand = carItem.brand,
        model = carItem.model,
        pathToPic = carItem.pathToPic,
        enable = carItem.enable
    )

    fun mapDbModelToEntity(carItemDbModel: CarItemDbModel) = CarItem(
        id = carItemDbModel.id,
        profile = carItemDbModel.profile,
        brand = carItemDbModel.brand,
        model = carItemDbModel.model,
        pathToPic = carItemDbModel.pathToPic,
        enable = carItemDbModel.enable
    )

    fun mapListDbModelToListEntity(list: List<CarItemDbModel>) = list.map {
        mapDbModelToEntity(it)
    }

    fun mapEntityToDbModel(soundItem: SoundItem) = SoundDbModel(
        id = soundItem.id,
        name = soundItem.name,
        five = soundItem.five,
        ten = soundItem.ten,
        fifteen = soundItem.fifteen,
        twenty = soundItem.twenty,
        thirty = soundItem.thirty,
        forty = soundItem.forty,
        fifty = soundItem.fifty,
        sixty = soundItem.sixty,
        seventy = soundItem.seventy,
        eighty = soundItem.eighty,
        ninety = soundItem.ninety,
        oneHundred = soundItem.oneHundred,
        oneHundredAndTen = soundItem.oneHundredAndTen,
        oneHundredAndTwenty = soundItem.oneHundredAndTwenty,
        oneHundredAndThirty = soundItem.oneHundredAndThirty,
        oneHundredAndForty = soundItem.oneHundredAndForty,
        unlimited = soundItem.unlimited,
        enable = soundItem.enable
    )

    fun mapDbModelToEntity(soundDbModel: SoundDbModel) = SoundItem(
        id = soundDbModel.id,
        name = soundDbModel.name,
        five = soundDbModel.five,
        ten = soundDbModel.ten,
        fifteen = soundDbModel.fifteen,
        twenty = soundDbModel.twenty,
        thirty = soundDbModel.thirty,
        forty = soundDbModel.forty,
        fifty = soundDbModel.fifty,
        sixty = soundDbModel.sixty,
        seventy = soundDbModel.seventy,
        eighty = soundDbModel.eighty,
        ninety = soundDbModel.ninety,
        oneHundred = soundDbModel.oneHundred,
        oneHundredAndTen = soundDbModel.oneHundredAndTen,
        oneHundredAndTwenty = soundDbModel.oneHundredAndTwenty,
        oneHundredAndThirty = soundDbModel.oneHundredAndThirty,
        oneHundredAndForty = soundDbModel.oneHundredAndForty,
        unlimited = soundDbModel.unlimited,
        enable = soundDbModel.enable
    )

    fun mapListDbModelSoundToListEntity(list: List<SoundDbModel>) = list.map {
        mapDbModelToEntity(it)
    }
}