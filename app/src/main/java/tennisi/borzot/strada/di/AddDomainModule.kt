package tennisi.borzot.strada.di

import dagger.Binds
import dagger.Module
import tennisi.borzot.strada.fragments.add.data.repository.CarListRepositoryImpl
import tennisi.borzot.strada.fragments.add.domain.repository.CarListRepository

@Module
interface AddDomainModule {

    @Binds
    fun bindCarListRepository(impl: CarListRepositoryImpl): CarListRepository

}