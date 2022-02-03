package tennisi.borzot.strada.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import tennisi.borzot.strada.fragments.news.data.network.NewsApi
import tennisi.borzot.strada.fragments.news.data.network.RetrofitInstance
import tennisi.borzot.strada.fragments.news.data.repository.NewsListRepositoryImpl
import tennisi.borzot.strada.fragments.news.domain.repository.NewsListRepository

@Module
interface NewsDomainModule {

    @ApplicationScope
    @Binds
    fun bindNewsListRepository(impl: NewsListRepositoryImpl): NewsListRepository

    companion object {
        @ApplicationScope
        @Provides
        fun provideAppInternet(): NewsApi {
            return RetrofitInstance.api
        }
    }
}