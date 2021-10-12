package tennisi.borzot.strada.homeScreen.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {

    private val _currentFragment = MutableLiveData<Int>()
    val currentFragment: LiveData<Int>
        get() = _currentFragment

    private val _currentNameFragment = MutableLiveData<String>()
    val currentNameFragment: LiveData<String>
        get() = _currentNameFragment


    fun setCurrentFragment(fragment: Int){
        _currentFragment.value = fragment
    }

    fun setCurrentNameFragment(nameFragment: String){
        _currentNameFragment.value = nameFragment
    }

}