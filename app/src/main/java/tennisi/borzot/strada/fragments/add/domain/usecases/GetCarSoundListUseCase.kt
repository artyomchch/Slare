package tennisi.borzot.strada.fragments.add.domain.usecases

import androidx.lifecycle.LiveData
import tennisi.borzot.strada.fragments.add.domain.entity.SoundItem
import tennisi.borzot.strada.fragments.add.domain.repository.CarListRepository
import javax.inject.Inject

class GetCarSoundListUseCase @Inject constructor(private val carListRepository: CarListRepository) {

    operator fun invoke(carItemId: Int): LiveData<List<SoundItem>> = carListRepository.getConfigListById(carItemId)

}

