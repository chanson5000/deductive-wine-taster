package com.wineguesser.deductive.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wineguesser.deductive.repository.VarietyDataRepository

class VarietyResultsViewModel : ViewModel() {
    private val varietyDataRepository: VarietyDataRepository = VarietyDataRepository()

    var appVariety: LiveData<String>? = null
        private set

    val actualLabel = MutableLiveData<String?>()
    val actualVariety = MutableLiveData<String?>()
    val actualCountry = MutableLiveData<String?>()
    val actualRegion = MutableLiveData<String?>()
    val actualQuality = MutableLiveData<String?>()
    val actualVintage = MutableLiveData<String?>()

    val userVariety = MutableLiveData<String?>()
    val userCountry = MutableLiveData<String?>()
    val userRegion = MutableLiveData<String?>()
    val userQuality = MutableLiveData<String?>()
    val userVintage = MutableLiveData<String?>()

    val resultButtonText = MutableLiveData<String?>()

    fun setUserVariety(userVariety: String?) {
        this.userVariety.value = userVariety
    }

    fun setUserCountry(userCountry: String?) {
        this.userCountry.value = userCountry
    }

    fun setUserRegion(userRegion: String?) {
        this.userRegion.value = userRegion
    }

    fun setUserQuality(userQuality: String?) {
        this.userQuality.value = userQuality
    }

    fun setUserVintage(userVintage: String?) {
        this.userVintage.value = userVintage
    }

    fun setAppVarietyById(isRedWine: Boolean, varietyId: String) {
        loadVariety(isRedWine, varietyId)
    }

    // Takes in boolean for red wine and the variety Id and retrieves the string from the
    private fun loadVariety(isRedWine: Boolean, varietyId: String) {
        appVariety = if (isRedWine) {
            varietyDataRepository.getRedVarietyNameById(varietyId)
        } else {
            varietyDataRepository.getWhiteVarietyNameById(varietyId)
        }
    }

    fun setActualLabel(actualLabel: String?) {
        this.actualLabel.value = actualLabel
    }

    fun setActualVariety(actualVariety: String?) {
        this.actualVariety.value = actualVariety
    }

    fun setActualCountry(actualCountry: String?) {
        this.actualCountry.value = actualCountry
    }

    fun setActualRegion(actualRegion: String?) {
        this.actualRegion.value = actualRegion
    }

    fun setActualQuality(actualQuality: String?) {
        this.actualQuality.value = actualQuality
    }

    fun setActualVintage(actualVintage: String?) {
        this.actualVintage.value = actualVintage
    }

    fun setResultButtonText(text: String?) {
        this.resultButtonText.value = text
    }
}
