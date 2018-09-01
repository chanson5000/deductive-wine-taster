package com.wineguesser.deductive.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

public class ErrorsFinalForm extends BaseObservable {

    public ErrorsFinalForm() {

    }

    private String errorVariety;
    private String errorCountry;
    private String errorRegion;
    private String errorQuality;
    private String errorVintage;

    @Bindable
    public String getErrorVariety() {
        return errorVariety;
    }

    public void setErrorVariety(String errorVariety) {
        this.errorVariety = errorVariety;
        notifyPropertyChanged(BR.errorVariety);
    }

    @Bindable
    public String getErrorCountry() {
        return errorCountry;
    }

    public void setErrorCountry(String errorCountry) {
        this.errorCountry = errorCountry;
        notifyPropertyChanged(BR.errorCountry);
    }

    @Bindable
    public String getErrorRegion() {
        return errorRegion;
    }

    public void setErrorRegion(String errorRegion) {
        this.errorRegion = errorRegion;
        notifyPropertyChanged(BR.errorRegion);
    }

    @Bindable
    public String getErrorQuality() {
        return errorQuality;
    }

    public void setErrorQuality(String errorQuality) {
        this.errorQuality = errorQuality;
        notifyPropertyChanged(BR.errorQuality);
    }

    @Bindable
    public String getErrorVintage() {
        return errorVintage;
    }

    public void setErrorVintage(String errorVintage) {
        this.errorVintage = errorVintage;
        notifyPropertyChanged(BR.errorVintage);
    }
}
