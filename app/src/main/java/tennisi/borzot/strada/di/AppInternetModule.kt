package tennisi.borzot.strada.di

import dagger.Module
import dagger.Provides
import tennisi.borzot.strada.fragments.news.data.network.NewsApi
import tennisi.borzot.strada.fragments.news.data.network.RetrofitInstance

@Module
class AppInternetModule {

    @Provides
    fun provideAppInternet(): NewsApi {
        return RetrofitInstance.api
    }
}