package tennisi.borzot.strada.di

import android.content.Context
import dagger.Module
import dagger.Provides
import tennisi.borzot.strada.fragments.add.data.database.AppDatabase
import tennisi.borzot.strada.fragments.add.data.database.CarListDao

@Module
class AppDatabaseModule {

    @Provides
    fun provideAppDatabase(context: Context): CarListDao{
        return AppDatabase.getInstance(context).carListDao()
    }
}