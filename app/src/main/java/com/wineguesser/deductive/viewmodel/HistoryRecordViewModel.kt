package com.wineguesser.deductive.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.wineguesser.deductive.model.ConclusionRecord;

public class HistoryRecordViewModel extends ViewModel {

    private final MutableLiveData<ConclusionRecord> conclusionRecord = new MutableLiveData<>();

    private final LiveData<String> appConclusionVariety =
            Transformations.map(conclusionRecord, ConclusionRecord::getAppConclusionVariety);

    private final LiveData<String> actualLabel =
            Transformations.map(conclusionRecord, ConclusionRecord::getActualLabel);
    private final LiveData<String> actualVariety =
            Transformations.map(conclusionRecord, ConclusionRecord::getActualVariety);
    private final LiveData<String> actualCountry =
            Transformations.map(conclusionRecord, ConclusionRecord::getActualCountry);
    private final LiveData<String> actualRegion =
            Transformations.map(conclusionRecord, ConclusionRecord::getActualRegion);
    private final LiveData<String> actualQuality =
            Transformations.map(conclusionRecord, ConclusionRecord::getActualQuality);
    private final LiveData<Integer> actualVintage =
            Transformations.map(conclusionRecord, ConclusionRecord::getActualVintage);

    private final LiveData<String> userConclusionVariety =
            Transformations.map(conclusionRecord, ConclusionRecord::getUserConclusionVariety);
    private final LiveData<String> userConclusionCountry =
            Transformations.map(conclusionRecord, ConclusionRecord::getUserConclusionCountry);
    private final LiveData<String> userConclusionRegion =
            Transformations.map(conclusionRecord, ConclusionRecord::getUserConclusionRegion);
    private final LiveData<String> userConclusionQuality =
            Transformations.map(conclusionRecord, ConclusionRecord::getUserConclusionQuality);
    private final LiveData<Integer> userConclusionVintage =
            Transformations.map(conclusionRecord, ConclusionRecord::getUserConclusionVintage);

    public MutableLiveData<ConclusionRecord> getConclusionRecord() {
        return conclusionRecord;
    }

    public void setConclusionRecord(ConclusionRecord conclusionRecord) {
        this.conclusionRecord.setValue(conclusionRecord);
    }

    public LiveData<String> getAppConclusionVariety() {
        return appConclusionVariety;
    }

    public LiveData<String> getActualLabel() { return actualLabel; }

    public LiveData<String> getActualVariety() {
        return actualVariety;
    }

    public LiveData<String> getActualCountry() {
        return actualCountry;
    }

    public LiveData<String> getActualRegion() {
        return actualRegion;
    }

    public LiveData<String> getActualQuality() {
        return actualQuality;
    }

    public LiveData<Integer> getActualVintage() {
        return actualVintage;
    }

    public LiveData<String> getUserConclusionVariety() {
        return userConclusionVariety;
    }

    public LiveData<String> getUserConclusionCountry() {
        return userConclusionCountry;
    }

    public LiveData<String> getUserConclusionRegion() {
        return userConclusionRegion;
    }

    public LiveData<String> getUserConclusionQuality() {
        return userConclusionQuality;
    }

    public LiveData<Integer> getUserConclusionVintage() {
        return userConclusionVintage;
    }
}
