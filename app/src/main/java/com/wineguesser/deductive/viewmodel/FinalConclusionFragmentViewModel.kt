package com.wineguesser.deductive.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FinalConclusionFragmentViewModel : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()

    fun setIsLoading(isLoading: Boolean) {
        this.isLoading.value = isLoading
    }
}
