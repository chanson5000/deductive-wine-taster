package com.wineguesser.deductive.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.wineguesser.deductive.repository.VarietyDataRepository;

public class VarietyResultsViewModel extends ViewModel {

    private VarietyDataRepository varietyDataRepository;

    private LiveData<String> appVariety;

    private MutableLiveData<String> actualVariety = new MutableLiveData<>();
    private MutableLiveData<String> actualCountry = new MutableLiveData<>();
    private MutableLiveData<String> actualRegion = new MutableLiveData<>();
    private MutableLiveData<String> actualQuality = new MutableLiveData<>();
    private MutableLiveData<String> actualVintage = new MutableLiveData<>();

    private MutableLiveData<String> userVariety = new MutableLiveData<>();
    private MutableLiveData<String> userCountry = new MutableLiveData<>();
    private MutableLiveData<String> userRegion = new MutableLiveData<>();
    private MutableLiveData<String> userQuality = new MutableLiveData<>();
    private MutableLiveData<String> userVintage = new MutableLiveData<>();

    private MutableLiveData<String> resultButtonText = new MutableLiveData<>();

    public VarietyResultsViewModel() {
        varietyDataRepository = new VarietyDataRepository();
    }

    public LiveData<String> getUserVariety() {
        return userVariety;
    }

    public void setUserVariety(String userVariety) {
        this.userVariety.setValue(userVariety);
    }

    public LiveData<String> getUserCountry() {
        return userCountry;
    }

    public void setUserCountry(String userCountry) {
        this.userCountry.setValue(userCountry);
    }

    public LiveData<String> getUserRegion() {
        return userRegion;
    }

    public void setUserRegion(String userRegion) {
        this.userRegion.setValue(userRegion);
    }

    public LiveData<String> getUserQuality() {
        return userQuality;
    }

    public void setUserQuality(String userQuality) {
        this.userQuality.setValue(userQuality);
    }

    public LiveData<String> getUserVintage() {
        return userVintage;
    }

    public void setUserVintage(String userVintage) {
        this.userVintage.setValue(userVintage);
    }

    public LiveData<String> getAppVariety() {
        return appVariety;
    }

    public void setAppVarietyById(boolean isRedWine, String varietyId) {
        loadVariety(isRedWine, varietyId);
    }

    // Takes in boolean for red wine and the variety Id and retrieves the string from the
    private void loadVariety(boolean isRedWine, String varietyId) {
        if (isRedWine) {
            this.appVariety = varietyDataRepository.getRedVarietyNameById(varietyId);
        } else {
            this.appVariety = varietyDataRepository.getWhiteVarietyNameById(varietyId);
        }
    }

    public MutableLiveData<String> getActualVariety() {
        return actualVariety;
    }

    public void setActualVariety(String actualVariety) {
        this.actualVariety.setValue(actualVariety);
    }

    public MutableLiveData<String> getActualCountry() {
        return actualCountry;
    }

    public void setActualCountry(String actualCountry) {
        this.actualCountry.setValue(actualCountry);
    }

    public MutableLiveData<String> getActualRegion() {
        return actualRegion;
    }

    public void setActualRegion(String actualRegion) {
        this.actualRegion.setValue(actualRegion);
    }

    public MutableLiveData<String> getActualQuality() {
        return actualQuality;
    }

    public void setActualQuality(String actualQuality) {
        this.actualQuality.setValue(actualQuality);
    }

    public MutableLiveData<String> getActualVintage() {
        return actualVintage;
    }

    public void setActualVintage(String actualVintage) {
        this.actualVintage.setValue(actualVintage);
    }

    public void setResultButtonText(String text) {
        this.resultButtonText.setValue(text);
    }
}
