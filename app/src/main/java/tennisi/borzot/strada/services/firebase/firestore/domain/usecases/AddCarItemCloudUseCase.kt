package tennisi.borzot.strada.services.firebase.firestore.domain.usecases

import tennisi.borzot.strada.services.firebase.firestore.domain.entity.CarItemCloud
import tennisi.borzot.strada.services.firebase.firestore.domain.repository.FirestoreRepository
import javax.inject.Inject

class AddCarItemCloudUseCase @Inject constructor(private val firestoreRepository: FirestoreRepository) {

    suspend operator fun invoke(carItemCloud: CarItemCloud) {
        firestoreRepository.addCarItemCloud(carItemCloud)
    }

}