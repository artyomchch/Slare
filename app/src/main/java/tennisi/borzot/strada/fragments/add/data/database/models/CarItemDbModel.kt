package tennisi.borzot.strada.fragments.add.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "car_items")
data class CarItemDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val brand: String,
    val model: String,
    val profile: String,
    @ColumnInfo(name = "path_to_pic")
    val pathToPic: String,
    val enable: Boolean
)
