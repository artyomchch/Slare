package tennisi.borzot.strada.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import tennisi.borzot.strada.fragments.add.data.database.AppDatabase
import tennisi.borzot.strada.fragments.add.data.database.CarListDao
import tennisi.borzot.strada.fragments.add.data.repository.CarListRepositoryImpl
import tennisi.borzot.strada.fragments.add.domain.repository.CarListRepository

@Module
interface AddDomainModule {

    @ApplicationScope
    @Binds
    fun bindCarListRepository(impl: CarListRepositoryImpl): CarListRepository

    companion object {

        @ApplicationScope
        @Provides
        fun provideAppDatabase(context: Context): CarListDao {
            return AppDatabase.getInstance(context).carListDao()
        }
    }

}