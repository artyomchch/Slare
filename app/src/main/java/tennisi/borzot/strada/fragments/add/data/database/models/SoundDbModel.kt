package tennisi.borzot.strada.fragments.add.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "config_sound")
data class SoundDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val five: Byte,
    val ten: Byte,
    val fifteen: Byte,
    val twenty: Byte,
    val thirty: Byte,
    val forty: Byte,
    val fifty: Byte,
    val sixty: Byte,
    val seventy: Byte,
    val eighty: Byte,
    val ninety: Byte,
    @ColumnInfo(name = "one_hundred")
    val oneHundred: Byte,
    @ColumnInfo(name = "one_hundred_and_ten")
    val oneHundredAndTen: Byte,
    @ColumnInfo(name = "one_hundred_and_twenty")
    val oneHundredAndTwenty: Byte,
    @ColumnInfo(name = "one_hundred_and_thirty")
    val oneHundredAndThirty: Byte,
    @ColumnInfo(name = "one_hundred_and_forty")
    val oneHundredAndForty: Byte,
    val unlimited: Byte,
    val enable : Boolean
    )
