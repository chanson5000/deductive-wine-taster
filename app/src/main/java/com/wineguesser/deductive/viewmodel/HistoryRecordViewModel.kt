package com.wineguesser.deductive.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.wineguesser.deductive.model.ConclusionRecord

class HistoryRecordViewModel : ViewModel() {
    val conclusionRecord = MutableLiveData<ConclusionRecord>()

    val appConclusionVariety: LiveData<String?> =
        conclusionRecord.map { it?.appConclusionVariety }

    val actualLabel: LiveData<String?> =
        conclusionRecord.map { it?.actualLabel }
    val actualVariety: LiveData<String?> =
        conclusionRecord.map { it?.actualVariety }
    val actualCountry: LiveData<String?> =
        conclusionRecord.map { it?.actualCountry }
    val actualRegion: LiveData<String?> =
        conclusionRecord.map { it?.actualRegion }
    val actualQuality: LiveData<String?> =
        conclusionRecord.map { it?.actualQuality }
    val actualVintage: LiveData<Int?> =
        conclusionRecord.map { it?.actualVintage }

    val userConclusionVariety: LiveData<String?> =
        conclusionRecord.map { it?.userConclusionVariety }
    val userConclusionCountry: LiveData<String?> =
        conclusionRecord.map { it?.userConclusionCountry }
    val userConclusionRegion: LiveData<String?> =
        conclusionRecord.map { it?.userConclusionRegion }
    val userConclusionQuality: LiveData<String?> =
        conclusionRecord.map { it?.userConclusionQuality }
    val userConclusionVintage: LiveData<Int?> =
        conclusionRecord.map { it?.userConclusionVintage }

    fun setConclusionRecord(conclusionRecord: ConclusionRecord) {
        this.conclusionRecord.value = conclusionRecord
    }
}
