package tennisi.borzot.strada.fragments.add.presentation.addFragmentUI

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tennisi.borzot.strada.fragments.add.domain.entity.CarItem
import tennisi.borzot.strada.fragments.add.domain.usecases.DeleteCarItemUseCase
import tennisi.borzot.strada.fragments.add.domain.usecases.EditCarItemUseCase
import tennisi.borzot.strada.fragments.add.domain.usecases.GetCarListUseCase
import tennisi.borzot.strada.services.firebase.firestore.domain.usecases.DeleteCarItemCloudUseCase
import javax.inject.Inject

class AddFragmentViewModel @Inject constructor(
    getCarListUseCase: GetCarListUseCase,
    private val deleteCarItemUseCase: DeleteCarItemUseCase,
    private val editCarItemUseCase: EditCarItemUseCase,
    private val deleteCarItemCloudUseCase: DeleteCarItemCloudUseCase,
) : ViewModel() {

    private val _observerItems = MutableLiveData<CarObserver<Boolean>>()
    val observerItems: LiveData<CarObserver<Boolean>>
        get() = _observerItems


    val carList = getCarListUseCase.invoke()


    fun observe() {
        if (carList.value.isNullOrEmpty()) _observerItems.value = CarObserver.Warning(true) else _observerItems.value = CarObserver.Success()
    }

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
        CoroutineScope(Dispatchers.IO).launch {
            deleteCarItemCloudUseCase(carItem.name)
        }
    }

}