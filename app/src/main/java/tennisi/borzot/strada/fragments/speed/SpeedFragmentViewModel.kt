package tennisi.borzot.strada.fragments.speed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class SpeedFragmentViewModel @Inject constructor() : ViewModel() {

    private val _observerSpeed = MutableLiveData<Float?>()
    val observerSpeed: LiveData<Float?>
        get() = _observerSpeed


    fun setSpeedValue(speedValue: Float) {
        _observerSpeed.postValue(speedValue)
    }
}