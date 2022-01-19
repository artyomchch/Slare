package tennisi.borzot.strada.services.firebase.firestore.data

import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import tennisi.borzot.strada.services.firebase.firestore.domain.entity.CarItemCloud
import tennisi.borzot.strada.services.firebase.firestore.domain.repository.FirestoreRepository
import javax.inject.Inject

class FirestoreRepositoryImpl @Inject constructor() : FirestoreRepository {

    private val db = Firebase.firestore

    override suspend fun addCarItemCloud(carItemCloud: CarItemCloud) {
        db.collection(COLLECTION_NAME_ID)
            .document(carItemCloud.name)
            .set(carItemCloud)
            .addOnFailureListener {
                Log.d("firestore", "addCarItemInCloud not add data ")
            }
    }

    override suspend fun deleteCarItemCloud(name: String) {
        db.collection(COLLECTION_NAME_ID)
            .document(name)
            .delete()
            .addOnFailureListener {
                Log.d("firestore", "addCarItemInCloud cannot delete data ")
            }
    }

    companion object {
        private val COLLECTION_NAME_ID = Firebase.auth.currentUser?.uid.toString()
    }
}