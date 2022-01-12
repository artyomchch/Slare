package tennisi.borzot.strada.di

import dagger.Binds
import dagger.Module
import tennisi.borzot.strada.fragments.news.data.repository.NewsListRepositoryImpl
import tennisi.borzot.strada.fragments.news.domain.repository.NewsListRepository

@Module
interface NewsDomainModule {

    @ApplicationScope
    @Binds
    fun bindNewsListRepository(impl: NewsListRepositoryImpl): NewsListRepository
}