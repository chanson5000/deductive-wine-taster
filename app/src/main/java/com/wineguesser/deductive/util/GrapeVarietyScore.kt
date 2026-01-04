package com.wineguesser.deductive.util

import android.os.AsyncTask
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.wineguesser.deductive.repository.DatabaseContract.DB_REFERENCE_RED_VARIETY_DESCRIPTORS
import com.wineguesser.deductive.repository.DatabaseContract.DB_REFERENCE_WHITE_VARIETY_DESCRIPTORS
import timber.log.Timber
import java.util.Collections
import java.util.HashMap

class GrapeVarietyScore(
    private val mContext: GrapeVarietyScoreResult,
    isRedWine: Boolean
) : AsyncTask<Map<String, Int>, Void, Void>() {

    private val mDatabaseReference: DatabaseReference =
        setDatabaseReference(isRedWine, FirebaseDatabase.getInstance())

    private fun setDatabaseReference(isRedWine: Boolean, database: FirebaseDatabase): DatabaseReference {
        return if (isRedWine) {
            database.getReference(DB_REFERENCE_RED_VARIETY_DESCRIPTORS)
        } else {
            database.getReference(DB_REFERENCE_WHITE_VARIETY_DESCRIPTORS)
        }
    }

    override fun doInBackground(vararg descriptorsMap: Map<String, Int>): Void? {
        val varietyScoresMap = HashMap<String, Int>()

        val listener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (varietyRecord in dataSnapshot.children) {
                    val currentVarietyScore = getCurrentVarietyScore(varietyRecord, descriptorsMap[0])
                    val varietyId = varietyRecord.key ?: continue
                    Timber.d("Putting score: %s, %s", varietyId, currentVarietyScore)
                    varietyScoresMap[varietyId] = currentVarietyScore
                }

                // Find the wine variety key with the highest score.
                val highestScoreId = getHighestScore(varietyScoresMap)

                if (highestScoreId != null) {
                    Timber.d("Result of wine scoring: %s", highestScoreId)
                    // We now have a wine variety to call back;
                    mContext.onGrapeResult(highestScoreId)
                } else {
                    mContext.onGrapeFailure()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                mContext.onGrapeFailure()
                Timber.e(databaseError.toString())
            }
        }
        // The listener that is triggering the code block above. Only needed to trigger once.
        mDatabaseReference.addListenerForSingleValueEvent(listener)

        return null
    }

    private fun getHighestScore(varietyScoresMap: Map<String, Int>): String? {
        return if (varietyScoresMap.isEmpty()) null else Collections.max(
            varietyScoresMap.entries
        ) { wineId, wineScore -> wineId.value - wineScore.value }.key
    }

    private fun getCurrentVarietyScore(
        varietyRecord: DataSnapshot,
        descriptorsMap: Map<String, Int>
    ): Int {
        var currentVarietyScore = 0

        for (varietyDescriptor in varietyRecord.children) {
            if (descriptorsMap.containsKey(varietyDescriptor.key)) {
                currentVarietyScore += calculateScoreIncrease(varietyDescriptor.getValue(Int::class.java))
            }
        }
        return currentVarietyScore
    }

    private fun calculateScoreIncrease(varietyDescriptorValue: Int?): Int {
        return if (varietyDescriptorValue != null && varietyDescriptorValue > 1) {
            // Two points for key indicator of variety (value is a 2 or higher)
            2
        } else {
            // One point for regular indicator
            1
        }
    }
}
