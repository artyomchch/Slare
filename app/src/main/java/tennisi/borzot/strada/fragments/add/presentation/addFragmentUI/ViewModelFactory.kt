package tennisi.borzot.strada.fragments.add.presentation.addFragmentUI

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import tennisi.borzot.strada.fragments.add.domain.usecases.DeleteCarItemUseCase
import tennisi.borzot.strada.fragments.add.domain.usecases.EditCarItemUseCase
import tennisi.borzot.strada.fragments.add.domain.usecases.GetCarListUseCase
import javax.inject.Inject

class ViewModelFactory @Inject constructor (
    private val getCarListUseCase: GetCarListUseCase,
    private val deleteCarItemUseCase: DeleteCarItemUseCase,
    private val editCarItemUseCase: EditCarItemUseCase


) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass == AddFragmentViewModel::class.java) {
            return AddFragmentViewModel(getCarListUseCase, deleteCarItemUseCase, editCarItemUseCase) as T
        }
        throw RuntimeException("Unknown view model class $modelClass")
    }
}