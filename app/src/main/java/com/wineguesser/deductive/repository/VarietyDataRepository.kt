package com.wineguesser.deductive.repository

import androidx.lifecycle.LiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import com.wineguesser.deductive.repository.DatabaseContract.DB_REFERENCE_RED_VARIETAL_DATA
import com.wineguesser.deductive.repository.DatabaseContract.DB_REFERENCE_WHITE_VARIETAL_DATA
import timber.log.Timber

class VarietyDataRepository : FirebaseRepository() {

    private val mRedReference: DatabaseReference = databaseInstance.getReference(DB_REFERENCE_RED_VARIETAL_DATA)
    private val mWhiteReference: DatabaseReference = databaseInstance.getReference(DB_REFERENCE_WHITE_VARIETAL_DATA)

    fun getRedVarietyNameById(id: String): LiveData<String?> {
        return VarietyName(mRedReference, id)
    }

    fun getWhiteVarietyNameById(id: String): LiveData<String?> {
        return VarietyName(mWhiteReference, id)
    }

    inner class VarietyName(reference: DatabaseReference, private val varietyId: String) : LiveData<String?>() {
        private val query: Query = reference
        private val listener: MyValueEventListener = MyValueEventListener()

        override fun onActive() {
            query.addValueEventListener(listener)
        }

        override fun onInactive() {
            query.removeEventListener(listener)
        }

        private inner class MyValueEventListener : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val dataObject = dataSnapshot.child(varietyId).child("variety").value
                if (dataObject != null) {
                    value = dataObject.toString()
                } else {
                    Timber.e("Unable to retrieve variety name.")
                    value = null
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Timber.e(databaseError.toException())
                value = null
            }
        }
    }
}
