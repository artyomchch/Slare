package tennisi.borzot.strada.fragments.add.presentation.carItemUI

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tennisi.borzot.strada.fragments.add.domain.entity.CarItem
import tennisi.borzot.strada.fragments.add.domain.usecases.AddCarItemUseCase
import tennisi.borzot.strada.fragments.add.domain.usecases.EditCarItemUseCase
import tennisi.borzot.strada.fragments.add.domain.usecases.GetCarItemUseCase
import tennisi.borzot.strada.services.firebase.firestore.domain.entity.CarItemCloud
import tennisi.borzot.strada.services.firebase.firestore.domain.usecases.AddCarItemCloudUseCase
import javax.inject.Inject

class CarItemViewModel @Inject constructor(
    private val getCarItemUseCase: GetCarItemUseCase,
    private val addCarItemUseCase: AddCarItemUseCase,
    private val editCarItemUseCase: EditCarItemUseCase,
    private val addCarItemCloudUseCase: AddCarItemCloudUseCase
) : ViewModel() {


    private val _errorInputProfile = MutableLiveData<Boolean>()
    val errorInputProfile: LiveData<Boolean>
        get() = _errorInputProfile

    private val _errorInputBrand = MutableLiveData<Boolean>()
    val errorInputBrand: LiveData<Boolean>
        get() = _errorInputBrand

    private val _errorInputModel = MutableLiveData<Boolean>()
    val errorInputModel: LiveData<Boolean>
        get() = _errorInputModel

    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit>
        get() = _shouldCloseScreen

    private val _carItem = MutableLiveData<CarItem>()
    val carItem: LiveData<CarItem>
        get() = _carItem


    fun getCarItem(carItemId: Int) {
        viewModelScope.launch {
            val item = getCarItemUseCase(carItemId)
            _carItem.value = item
        }
    }

    fun addCarItem(inputBrand: String?, inputModel: String?, inputProfile: String?, imageUri: String, enable: Boolean) {

        val profile = parseName(inputProfile)
        val brand = parseName(inputBrand)
        val model = parseName(inputModel)
        val fieldsValid = validateInput(profile, brand, model)
        if (fieldsValid) {
            viewModelScope.launch {
                val carItem = CarItem(brand, model, profile, pathToPic = imageUri, enable)
                addCarItemUseCase(carItem)
                finishWork()
            }
            CoroutineScope(Dispatchers.IO).launch {
                val carItemCloud = CarItemCloud(brand, model, profile, true)
                addCarItemCloudUseCase(carItemCloud)
            }
        }
    }

    fun editCarItem(inputBrand: String?, inputModel: String?, inputProfile: String?, imageUri: String, enable: Boolean) {

        val brand = parseName(inputBrand)
        val model = parseName(inputModel)
        val profile = parseName(inputProfile)
        val fieldsValid = validateInput(brand, model, profile)
        if (fieldsValid) {
            _carItem.value?.let {
                viewModelScope.launch {
                    val item = it.copy(brand = brand, model = model, profile = profile, pathToPic = imageUri, enable = enable)
                    editCarItemUseCase(item)
                    finishWork()
                }
            }
        }

    }

//    private fun addCarItemInCloud(name: String, brand: String, model: String) {
//        val db = Firebase.firestore
//        val car = CarItemCloud(name, brand, model, false)
//        db.collection("userCar")
//            .add(car)
//            .addOnFailureListener {
//                Log.d("firestore", "addCarItemInCloud not add data ")
//            }
//    }

    fun resetErrorInputProfile() {
        _errorInputProfile.value = false
    }

    fun resetErrorInputBrand() {
        _errorInputBrand.value = false
    }

    fun resetErrorInputModel() {
        _errorInputModel.value = false
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    private fun validateInput(brand: String, model: String, profile: String): Boolean {
        var result = true
        if (profile.isBlank()) {
            _errorInputProfile.value = true
            result = false
        }
        if (brand.isBlank()) {
            _errorInputBrand.value = true
            result = false
        }
        if (model.isBlank()) {
            _errorInputModel.value = true
            result = false
        }
        return result
    }

    private fun finishWork() {
        _shouldCloseScreen.value = Unit
    }

}