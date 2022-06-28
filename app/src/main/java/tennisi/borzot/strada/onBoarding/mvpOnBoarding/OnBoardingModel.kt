package tennisi.borzot.strada.onBoarding.mvpOnBoarding

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tennisi.borzot.strada.R
import tennisi.borzot.strada.splash.DataStoreRepository

class OnBoardingModel : OnBoardingInterface.Model {

    private var dataList: MutableList<OnBoardingData> = arrayListOf()

    override fun createData(application: Application) {
        dataList.add(
            OnBoardingData(
                application.getString(R.string.speed_limit),
                application.getString(R.string.desc_speed_limit),
                R.drawable.ic_speed_limit
            )
        )
        dataList.add(
            OnBoardingData(
                application.getString(R.string.destructed),
                application.getString(R.string.desc_destructed),
                R.drawable.ic_destructed
            )
        )
        dataList.add(
            OnBoardingData(
                application.getString(R.string.permissions),
                application.getString(R.string.desc_permissions),
                R.drawable.ic_permission
            )
        )
    }

    override fun savePrefData(application: Application) {
        val saveData = DataStoreRepository(application)
        CoroutineScope(Dispatchers.IO).launch {
            saveData.saveToDataStore(DataStoreRepository.FIRST)
        }

    }

    override fun getDataTitle(): MutableList<OnBoardingData> = dataList

}