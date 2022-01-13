package tennisi.borzot.strada.di

import dagger.Binds
import dagger.Module
import tennisi.borzot.strada.fragments.news.data.repository.NewsListRepositoryImpl
import tennisi.borzot.strada.fragments.news.domain.repository.NewsListRepository
import tennisi.borzot.strada.services.firebase.firestore.data.FirestoreRepositoryImpl
import tennisi.borzot.strada.services.firebase.firestore.domain.repository.FirestoreRepository

@Module
interface FirestoreModule {

    @ApplicationScope
    @Binds
    fun bindFirestoreRepository(impl: FirestoreRepositoryImpl): FirestoreRepository
}