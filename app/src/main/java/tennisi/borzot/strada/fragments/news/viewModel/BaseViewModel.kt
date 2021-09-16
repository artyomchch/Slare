package tennisi.borzot.strada.fragments.news.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tennisi.borzot.strada.R
import tennisi.borzot.strada.fragments.news.promisses.SuccessResult


typealias MutableLiveResult<T> = MutableLiveData<Result<T>>

class Event<T>(
    private val value: T
) {
    private var handled: Boolean = false

    fun getValue(): T? {
        if (handled) return null
        handled = true
        return value
    }
}

open class BaseViewModel : ViewModel() {


    fun <T> into(liveResult: MutableLiveResult<T>, block: suspend () -> T){
        viewModelScope.launch {
//            try {
//                liveResult.postValue(SuccessResult())
//            }
        }
    }

//    private val tasks = mutableListOf<Task<*>>()
//
//    override fun onCleared() {
//        super.onCleared()
//        tasks.forEach { it.cancel() }
//    }
//
//    fun <T> Task<T>.autoCancel() {
//        tasks.add(this)
//    }

}