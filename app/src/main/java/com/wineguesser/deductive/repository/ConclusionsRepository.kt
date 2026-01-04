package com.wineguesser.deductive.repository

import androidx.lifecycle.LiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import com.wineguesser.deductive.model.ConclusionRecord
import timber.log.Timber
import java.util.Collections

class ConclusionsRepository : FirebaseRepository() {

    private val mConclusionsReference: DatabaseReference = databaseInstance.getReference("conclusions")

    fun clearUserConclusions(uid: String) {
        mConclusionsReference.child(uid).removeValue()
    }

    fun saveConclusionRecord(uid: String, conclusionRecord: ConclusionRecord) {
        // Get the reference of where our new conclusion record wil be pushed.
        val newConclusionRecordReference = mConclusionsReference.child(uid).push()

        // Add the new conclusion record to the database.
        newConclusionRecordReference.setValue(conclusionRecord)
    }

    fun removeConclusionRecord(userId: String, conclusionRecord: ConclusionRecord) {
        conclusionRecord.conclusionId?.let {
            mConclusionsReference.child(userId).child(it).removeValue()
        }
    }

    fun getConclusionsForUser(uid: String): LiveData<List<ConclusionRecord>> {
        return ConclusionsList(uid)
    }

    inner class ConclusionsList(uid: String) : LiveData<List<ConclusionRecord>>() {
        private val query: Query = mConclusionsReference.child(uid)
        private val listener: MyValueEventListener = MyValueEventListener()

        override fun onActive() {
            query.addValueEventListener(listener)
        }

        override fun onInactive() {
            query.removeEventListener(listener)
        }

        private inner class MyValueEventListener : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    val data = ArrayList<ConclusionRecord>()

                    for (entry in dataSnapshot.children) {
                        val conclusionId = entry.key
                        val conclusionRecord = entry.getValue(ConclusionRecord::class.java)
                        if (conclusionRecord != null) {
                            conclusionRecord.conclusionId = conclusionId
                            data.add(conclusionRecord)
                        }
                    }
                    Collections.reverse(data)
                    value = data
                } else {
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
