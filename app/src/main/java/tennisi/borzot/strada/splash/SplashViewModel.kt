package tennisi.borzot.strada.splash

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData

class SplashViewModel(application: Application): AndroidViewModel(application) {
    private val repository = DataStoreRepository(application)
    val readFromDataStore = repository.readFromDataStore.asLiveData()
}