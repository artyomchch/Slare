package tennisi.borzot.strada.fragments.add.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import tennisi.borzot.strada.fragments.add.data.database.models.CarConfigSoundDbModel
import tennisi.borzot.strada.fragments.add.data.database.models.CarItemDbModel
import tennisi.borzot.strada.fragments.add.data.database.models.SoundDbModel

@Database(entities = [CarItemDbModel::class, SoundDbModel::class, CarConfigSoundDbModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun carListDao(): CarListDao

    abstract fun carConfigDao(): CarConfigDao


    companion object {

        private var db: AppDatabase? = null
        private val LOCK = Any()
        private const val DB_NAME = "car_item.db"
        private const val DB_CONFIG = "car_config_sound.db"


        fun getInstance(context: Context): AppDatabase {
            synchronized(LOCK) {
                db?.let {
                    return it
                }
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DB_NAME
                ).build()
                db = instance
                return instance
            }
        }

        fun getInstanceConfig(context: Context): AppDatabase {
            synchronized(LOCK) {
                db?.let {
                    return it
                }
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DB_CONFIG
                ).build()
                db = instance
                return instance
            }
        }
    }
}