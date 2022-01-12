package tennisi.borzot.strada.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import tennisi.borzot.strada.fragments.add.presentation.addFragmentUI.AddFragmentViewModel
import tennisi.borzot.strada.fragments.add.presentation.carItemUI.CarItemViewModel
import tennisi.borzot.strada.fragments.news.presentation.newsFragment.NewsFragmentViewModel
import tennisi.borzot.strada.fragments.news.presentation.sourceFragment.SourceFragmentViewModel

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(AddFragmentViewModel::class)
    @Binds
    fun bindAddFragmentViewModel(impl: AddFragmentViewModel): ViewModel

    @IntoMap
    @ViewModelKey(CarItemViewModel::class)
    @Binds
    fun bindCarItemViewModel(impl: CarItemViewModel): ViewModel

    @IntoMap
    @ViewModelKey(NewsFragmentViewModel::class)
    @Binds
    fun bindNewsFragmentViewModel(impl: NewsFragmentViewModel): ViewModel

    @IntoMap
    @ViewModelKey(SourceFragmentViewModel::class)
    @Binds
    fun bindSourceFragmentViewModel(impl: SourceFragmentViewModel): ViewModel

}