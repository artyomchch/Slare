package tennisi.borzot.strada.fragments.add.presentation

import androidx.lifecycle.ViewModel
import tennisi.borzot.strada.fragments.add.data.CarListRepositoryImpl
import tennisi.borzot.strada.fragments.add.domain.DeleteCarItemUseCase
import tennisi.borzot.strada.fragments.add.domain.EditCarItemUseCase
import tennisi.borzot.strada.fragments.add.domain.GetCarListUseCase

class AddFragmentViewModel : ViewModel() {


    private val repository = CarListRepositoryImpl

    private val getCarListUseCase = GetCarListUseCase(repository)
    private val deleteCarItemUseCase = DeleteCarItemUseCase(repository)
    private val editCarItemUseCase = EditCarItemUseCase(repository)

    val carList = getCarListUseCase.getCarList()

}