package tennisi.borzot.strada.fragments.add.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import tennisi.borzot.strada.fragments.add.data.database.models.CarItemDbModel

@Dao
interface CarListDao {

    @Query("SELECT * FROM car_items ORDER BY enable DESC")
    fun getCarList(): LiveData<List<CarItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCarItem(carItemDbModel: CarItemDbModel)

    @Query("DELETE FROM car_items WHERE id=:carItemId")
    suspend fun deleteCarItem(carItemId: Int)

    @Query("SELECT * FROM car_items WHERE id=:carItemId LIMIT 1")
    suspend fun getCarItem(carItemId: Int): CarItemDbModel

    @Query("UPDATE car_items SET enable = CASE WHEN id=:carItemId THEN 1 ELSE 0 END")
    suspend fun resetEnableFromCar(carItemId: Int)

    @Query("UPDATE car_items SET enable = CASE WHEN id = (SELECT max(id) FROM car_items) THEN 1 ELSE 0 END")
    suspend fun resetEnableFromCarWithAddNew()


}