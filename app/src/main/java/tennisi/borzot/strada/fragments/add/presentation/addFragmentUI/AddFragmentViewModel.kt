package tennisi.borzot.strada.fragments.add.presentation.addFragmentUI

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tennisi.borzot.strada.fragments.add.domain.entity.CarItem
import tennisi.borzot.strada.fragments.add.domain.usecases.DeleteCarItemUseCase
import tennisi.borzot.strada.fragments.add.domain.usecases.EditCarItemUseCase
import tennisi.borzot.strada.fragments.add.domain.usecases.GetCarListUseCase
import javax.inject.Inject

class AddFragmentViewModel @Inject constructor(
    getCarListUseCase: GetCarListUseCase,
    private val deleteCarItemUseCase: DeleteCarItemUseCase,
    private val editCarItemUseCase: EditCarItemUseCase
) : ViewModel() {


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