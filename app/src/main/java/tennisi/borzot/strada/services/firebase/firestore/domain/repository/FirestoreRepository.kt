package tennisi.borzot.strada.services.firebase.firestore.domain.repository

import tennisi.borzot.strada.services.firebase.firestore.domain.entity.CarItemCloud

interface FirestoreRepository {

    suspend fun addCarItemCloud(carItemCloud: CarItemCloud)

    suspend fun deleteCarItemCloud(name: String)
}