package tennisi.borzot.strada.fragments.add.presentation.addFragmentUI

import androidx.lifecycle.ViewModel
import tennisi.borzot.strada.fragments.add.data.CarListRepositoryImpl
import tennisi.borzot.strada.fragments.add.domain.entity.CarItem
import tennisi.borzot.strada.fragments.add.domain.usecases.DeleteCarItemUseCase
import tennisi.borzot.strada.fragments.add.domain.usecases.EditCarItemUseCase
import tennisi.borzot.strada.fragments.add.domain.usecases.GetCarListUseCase

class AddFragmentViewModel : ViewModel() {


    private val repository = CarListRepositoryImpl

    private val getCarListUseCase = GetCarListUseCase(repository)
    private val deleteCarItemUseCase = DeleteCarItemUseCase(repository)
    private val editCarItemUseCase = EditCarItemUseCase(repository)

    fun changeEnableState(carItem: CarItem){
        val newItem = carItem.copy(enable = !carItem.enable)
        editCarItemUseCase(newItem)
    }

    fun deleteCarItem(carItem: CarItem) {
        deleteCarItemUseCase(carItem)
    }

    val carList = getCarListUseCase.invoke()

}