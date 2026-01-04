package com.wineguesser.deductive.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ConclusionInputErrorsViewModel : ViewModel() {
    val errorVariety = MutableLiveData<String?>()
    val errorCountry = MutableLiveData<String?>()
    val errorRegion = MutableLiveData<String?>()
    val errorQuality = MutableLiveData<String?>()
    val errorVintage = MutableLiveData<String?>()
    val errorLabel = MutableLiveData<String?>()

    fun setErrorVariety(errorVariety: String?) {
        this.errorVariety.value = errorVariety
    }

    fun setErrorCountry(errorCountry: String?) {
        this.errorCountry.value = errorCountry
    }

    fun setErrorRegion(errorRegion: String?) {
        this.errorRegion.value = errorRegion
    }

    fun setErrorQuality(errorQuality: String?) {
        this.errorQuality.value = errorQuality
    }

    fun setErrorVintage(errorVintage: String?) {
        this.errorVintage.value = errorVintage
    }

    fun setErrorLabel(errorLabel: String?) {
        this.errorLabel.value = errorLabel
    }
}
