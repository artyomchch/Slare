package tennisi.borzot.strada.fragments.add.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import tennisi.borzot.strada.fragments.add.data.database.models.CarConfigSoundDbModel
import tennisi.borzot.strada.fragments.add.data.database.models.SoundDbModel
import tennisi.borzot.strada.fragments.add.domain.entity.SoundItem

@Dao
interface CarConfigDao {

    @Query("SELECT * " +
            "FROM config_sound " +
            "LEFT JOIN car_config_sound " +
            "  ON config_sound.id = car_config_sound.config_id WHERE car_config_sound.car_id = :carId")
    fun getSoundAndConfig(carId: Int): LiveData<Map<SoundDbModel, CarConfigSoundDbModel?>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setActiveFlagForCarConfigSound(carConfigSoundDbModel: CarConfigSoundDbModel)

//    @Query("SELECT * FROM car_items")
//    fun getCarList(): LiveData<List<CarItemDbModel>>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun addCarItem(carItemDbModel: CarItemDbModel)
//
//    @Query("DELETE FROM car_items WHERE id=:carItemId")
//    suspend fun deleteCarItem(carItemId: Int)
//
//    @Query("SELECT * FROM car_items WHERE id=:carItemId LIMIT 1")
//    suspend fun getCarItem(carItemId: Int): CarItemDbModel
}