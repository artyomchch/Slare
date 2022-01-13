package tennisi.borzot.strada.services.firebase.firestore.domain.usecases

import tennisi.borzot.strada.services.firebase.firestore.domain.entity.CarItemCloud
import tennisi.borzot.strada.services.firebase.firestore.domain.repository.FirestoreRepository
import javax.inject.Inject

class DeleteCarItemCloudUseCase @Inject constructor(private val firestoreRepository: FirestoreRepository) {

    suspend operator fun invoke(name: String) {
        firestoreRepository.deleteCarItemCloud(name)
    }

}