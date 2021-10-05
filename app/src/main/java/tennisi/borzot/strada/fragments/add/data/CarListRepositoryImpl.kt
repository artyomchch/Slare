package tennisi.borzot.strada.fragments.add.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import tennisi.borzot.strada.fragments.add.domain.CarItem
import tennisi.borzot.strada.fragments.add.domain.CarListRepository
import kotlin.random.Random

object CarListRepositoryImpl : CarListRepository {

    private val carListLD = MutableLiveData<List<CarItem>>()
    private val carList = sortedSetOf<CarItem>({ o1, o2 -> o1.id.compareTo(o2.id) })

    private var autoIncrementId = 0

    init {
        for (i in 0 until 20) {
            val item = CarItem("Name $i", "Brand $i", "Model $i", Random.nextBoolean())
            addCarItem(item)
        }
    }

    override fun editCarItem(carItem: CarItem) {
        val oldElement = getCarItem(carItem.id)
        carList.remove(oldElement)
        addCarItem(carItem)
    }

    override fun addCarItem(carItem: CarItem) {
        if (carItem.id == CarItem.UNDEFINED_ID) {
            carItem.id = autoIncrementId++
        }
        carList.add(carItem)
        updateList()
    }

    override fun getCarItem(carItemId: Int): CarItem {
        return carList.find {
            it.id == carItemId
        } ?: throw RuntimeException("Element with id $carItemId not found")
    }

    override fun deleteCarItem(carItem: CarItem) {
        carList.remove(carItem)
        updateList()
    }

    override fun getCarList(): LiveData<List<CarItem>> = carListLD

    private fun updateList() {
        carListLD.value = carList.toList()
    }

}