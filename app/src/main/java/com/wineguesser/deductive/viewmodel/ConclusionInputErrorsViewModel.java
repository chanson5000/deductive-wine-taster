package com.wineguesser.deductive.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ConclusionInputErrorsViewModel extends ViewModel {

    private final MutableLiveData<String> errorVariety = new MutableLiveData<>();
    private final MutableLiveData<String> errorCountry = new MutableLiveData<>();
    private final MutableLiveData<String> errorRegion = new MutableLiveData<>();
    private final MutableLiveData<String> errorQuality = new MutableLiveData<>();
    private final MutableLiveData<String> errorVintage = new MutableLiveData<>();
    private final MutableLiveData<String> errorLabel = new MutableLiveData<>();

    public LiveData<String> getErrorVariety() {
        return errorVariety;
    }

    public void setErrorVariety(String errorVariety) {
        this.errorVariety.setValue(errorVariety);
    }

    public LiveData<String> getErrorCountry() {
        return errorCountry;
    }

    public void setErrorCountry(String errorCountry) {
        this.errorCountry.setValue(errorCountry);
    }

    public LiveData<String> getErrorRegion() {
        return errorRegion;
    }

    public void setErrorRegion(String errorRegion) {
        this.errorRegion.setValue(errorRegion);
    }

    public LiveData<String> getErrorQuality() {
        return errorQuality;
    }

    @SuppressWarnings("unused")
    public void setErrorQuality(String errorQuality) {
        this.errorQuality.setValue(errorQuality);
    }

    public LiveData<String> getErrorVintage() {
        return errorVintage;
    }

    public void setErrorVintage(String errorVintage) {
        this.errorVintage.setValue(errorVintage);
    }

    public LiveData<String> getErrorLabel() {
        return errorLabel;
    }

    public void setErrorLabel(String errorLabel) {
        this.errorLabel.setValue(errorLabel);
    }
}
