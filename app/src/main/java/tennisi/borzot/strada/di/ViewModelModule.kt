package tennisi.borzot.strada.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey
import tennisi.borzot.strada.fragments.add.presentation.addFragmentUI.AddFragmentViewModel
import tennisi.borzot.strada.fragments.add.presentation.carItemUI.CarItemViewModel

@Module
interface ViewModelModule {

    @IntoMap
    @StringKey("AddFragmentViewModel")
    @Binds
    fun bindAddFragmentViewModel(impl: AddFragmentViewModel): ViewModel

    @IntoMap
    @StringKey("CarItemViewModel")
    @Binds
    fun bindCarItemViewModel(impl: CarItemViewModel): ViewModel
}