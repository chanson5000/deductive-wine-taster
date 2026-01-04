package com.wineguesser.deductive.util

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.wineguesser.deductive.repository.DatabaseContract.DB_REFERENCE_RED_VARIETY_DESCRIPTORS
import com.wineguesser.deductive.repository.DatabaseContract.DB_REFERENCE_WHITE_VARIETY_DESCRIPTORS
import kotlinx.coroutines.suspendCancellableCoroutine
import timber.log.Timber
import java.util.Collections
import java.util.HashMap
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class GrapeVarietyScore(isRedWine: Boolean) {

    private val databaseReference: DatabaseReference =
        setDatabaseReference(isRedWine, Firebase.database)

    private fun setDatabaseReference(isRedWine: Boolean, database: FirebaseDatabase): DatabaseReference {
        return if (isRedWine) {
            database.getReference(DB_REFERENCE_RED_VARIETY_DESCRIPTORS)
        } else {
            database.getReference(DB_REFERENCE_WHITE_VARIETY_DESCRIPTORS)
        }
    }

    suspend fun calculateScore(descriptorsMap: Map<String, Int>): String? {
        return suspendCancellableCoroutine { continuation ->
            val listener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val varietyScoresMap = HashMap<String, Int>()
                    for (varietyRecord in dataSnapshot.children) {
                        val currentVarietyScore = getCurrentVarietyScore(varietyRecord, descriptorsMap)
                        val varietyId = varietyRecord.key ?: continue
                        Timber.d("Putting score: %s, %s", varietyId, currentVarietyScore)
                        varietyScoresMap[varietyId] = currentVarietyScore
                    }

                    // Find the wine variety key with the highest score.
                    val highestScoreId = getHighestScore(varietyScoresMap)
                    Timber.d("Result of wine scoring: %s", highestScoreId)
                    continuation.resume(highestScoreId)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Timber.e(databaseError.toException())
                    continuation.resumeWithException(databaseError.toException())
                }
            }
            databaseReference.addListenerForSingleValueEvent(listener)

            continuation.invokeOnCancellation {
                databaseReference.removeEventListener(listener)
            }
        }
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
