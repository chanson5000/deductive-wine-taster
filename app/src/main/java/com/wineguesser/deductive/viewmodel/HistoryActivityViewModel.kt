package com.wineguesser.deductive.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wineguesser.deductive.model.ConclusionRecord
import com.wineguesser.deductive.repository.ConclusionsRepository

class HistoryActivityViewModel : ViewModel() {
    private val conclusionsRepository: ConclusionsRepository = ConclusionsRepository()
    val noData = MutableLiveData<Boolean>()

    fun getUserConclusions(uid: String): LiveData<List<ConclusionRecord>> {
        return conclusionsRepository.getConclusionsForUser(uid)
    }

    fun setNoData(isData: Boolean) {
        this.noData.value = isData
    }
}
