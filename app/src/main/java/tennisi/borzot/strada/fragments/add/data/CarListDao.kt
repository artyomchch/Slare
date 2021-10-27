package tennisi.borzot.strada.fragments.add.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CarListDao {

    @Query("SELECT * FROM car_items")
    fun getCarList(): LiveData<List<CarItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCarItem(carItemDbModel: CarItemDbModel)

    @Query("DELETE FROM car_items WHERE id=:carItemId")
    fun deleteCarItem(carItemId: Int)

    @Query("SELECT * FROM car_items WHERE id=:carItemId LIMIT 1")
    fun getCarItem(carItemId: Int): CarItemDbModel
}