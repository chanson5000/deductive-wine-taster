package com.wineguesser.deductive.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;

import com.wineguesser.deductive.BR;
import com.wineguesser.deductive.repository.VarietyDataRepository;

public class VarietyResultsViewModel extends ViewModel implements Observable {

    private PropertyChangeRegistry callbacks = new PropertyChangeRegistry();
    private VarietyDataRepository varietyDataRepository;

    private LiveData<String> appVariety;

    private MutableLiveData<String> actualVariety = new MutableLiveData<>();
    private MutableLiveData<String> actualCountry = new MutableLiveData<>();
    private MutableLiveData<String> actualRegion = new MutableLiveData<>();
    private MutableLiveData<String> actualQuality = new MutableLiveData<>();
    private MutableLiveData<Integer> actualVintage = new MutableLiveData<>();

    private MutableLiveData<String> userVariety = new MutableLiveData<>();
    private MutableLiveData<String> userCountry = new MutableLiveData<>();
    private MutableLiveData<String> userRegion = new MutableLiveData<>();
    private MutableLiveData<String> userQuality = new MutableLiveData<>();
    private MutableLiveData<Integer> userVintage = new MutableLiveData<>();

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

    public LiveData<Integer> getUserVintage() {
        return userVintage;
    }

    public void setUserVintage (Integer userVintage) {
        this.userVintage.setValue(userVintage);
    }

    public LiveData<String> getAppVariety() {
        return appVariety;
    }

    public void setAppVarietyById(boolean isRedWine, String varietyId) {
        loadVariety(isRedWine, varietyId);
    }

    private void loadVariety(boolean isRedWine, String varietyId) {
        if (isRedWine) {
            this.appVariety = varietyDataRepository.getRedVarietyNameById(varietyId);
        } else {
            this.appVariety = varietyDataRepository.getWhiteVarietyNameById(varietyId);
        }
    }

    @Bindable
    public MutableLiveData<String> getActualVariety() {
        return actualVariety;
    }

    public void setActualVariety(String actualVariety) {
        this.actualVariety.setValue(actualVariety);
        notifyPropertyChanged(BR.actualVariety);
    }

    @Bindable
    public MutableLiveData<String> getActualCountry() {
        return actualCountry;
    }

    public void setActualCountry(String actualCountry) {
        this.actualCountry.setValue(actualCountry);
        notifyPropertyChanged(BR.actualCountry);
    }

    @Bindable
    public MutableLiveData<String> getActualRegion() {
        return actualRegion;
    }

    public void setActualRegion(String actualRegion) {
        this.actualRegion.setValue(actualRegion);
        notifyPropertyChanged(BR.actualRegion);
    }

    @Bindable
    public MutableLiveData<String> getActualQuality() {
        return actualQuality;
    }

    public void setActualQuality(String actualQuality) {
        this.actualQuality.setValue(actualQuality);
        notifyPropertyChanged(BR.actualQuality);
    }

    @Bindable
    public MutableLiveData<Integer> getActualVintage() {
        return actualVintage;
    }

    public void setActualVintage(Integer actualVintage) {
        this.actualVintage.setValue(actualVintage);
        notifyPropertyChanged(BR.actualVintage);
    }

    @Override
    public void addOnPropertyChangedCallback(Observable.OnPropertyChangedCallback callback) {
        callbacks.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(Observable.OnPropertyChangedCallback callback) {
        callbacks.remove(callback);
    }

    private void notifyPropertyChanged(int fieldId) {
        callbacks.notifyCallbacks(this, fieldId, null);
    }
}
