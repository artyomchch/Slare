package tennisi.borzot.strada.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import tennisi.borzot.strada.fragments.add.presentation.addFragmentUI.AddFragmentViewModel
import tennisi.borzot.strada.fragments.add.presentation.carItemUI.CarItemViewModel

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
}