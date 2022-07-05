package tennisi.borzot.strada.fragments.add.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index


@Entity(
    tableName = "car_config_sound",
    primaryKeys = ["car_id", "config_id"],
    indices = [
        Index("config_id")
    ],
    foreignKeys = [
        ForeignKey(
            entity = CarItemDbModel::class,
            parentColumns = ["id"],
            childColumns = ["car_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = SoundDbModel::class,
            parentColumns = ["id"],
            childColumns = ["config_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
    )
data class CarConfigSoundDbModel(
    @ColumnInfo(name = "car_id")
    val carId: Int,
    @ColumnInfo(name = "config_id")
    val configId: Int
)
