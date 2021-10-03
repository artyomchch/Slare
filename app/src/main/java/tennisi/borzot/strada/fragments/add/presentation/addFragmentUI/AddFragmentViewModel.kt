package tennisi.borzot.strada.fragments.add.presentation.addFragmentUI

import androidx.lifecycle.ViewModel
import tennisi.borzot.strada.fragments.add.data.CarListRepositoryImpl
import tennisi.borzot.strada.fragments.add.domain.CarItem
import tennisi.borzot.strada.fragments.add.domain.DeleteCarItemUseCase
import tennisi.borzot.strada.fragments.add.domain.EditCarItemUseCase
import tennisi.borzot.strada.fragments.add.domain.GetCarListUseCase

class AddFragmentViewModel : ViewModel() {


    private val repository = CarListRepositoryImpl

    private val getCarListUseCase = GetCarListUseCase(repository)
    private val deleteCarItemUseCase = DeleteCarItemUseCase(repository)
    private val editCarItemUseCase = EditCarItemUseCase(repository)

    fun changeEnableState(carItem: CarItem){
        val newItem = carItem.copy(enable = !carItem.enable)
        editCarItemUseCase.editCarItem(newItem)
    }

    fun deleteCarItem(carItem: CarItem) {
        deleteCarItemUseCase.deleteCarItem(carItem)
    }

    val carList = getCarListUseCase.getCarList()

}