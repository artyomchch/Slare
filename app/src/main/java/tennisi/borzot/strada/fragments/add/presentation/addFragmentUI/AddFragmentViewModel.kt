package tennisi.borzot.strada.fragments.add.presentation.addFragmentUI

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tennisi.borzot.strada.fragments.add.data.repository.CarListRepositoryImpl
import tennisi.borzot.strada.fragments.add.domain.entity.CarItem
import tennisi.borzot.strada.fragments.add.domain.usecases.DeleteCarItemUseCase
import tennisi.borzot.strada.fragments.add.domain.usecases.EditCarItemUseCase
import tennisi.borzot.strada.fragments.add.domain.usecases.GetCarListUseCase

class AddFragmentViewModel(application: Application) : AndroidViewModel(application) {


    private val repository = CarListRepositoryImpl(application)

    private val getCarListUseCase = GetCarListUseCase(repository)
    private val deleteCarItemUseCase = DeleteCarItemUseCase(repository)
    private val editCarItemUseCase = EditCarItemUseCase(repository)

    val carList = getCarListUseCase.invoke()

    fun changeEnableState(carItem: CarItem) {
        viewModelScope.launch {
            val newItem = carItem.copy(enable = !carItem.enable)
            editCarItemUseCase(newItem)
        }
    }

    fun deleteCarItem(carItem: CarItem) {
        viewModelScope.launch {
            deleteCarItemUseCase(carItem)
        }
    }

}